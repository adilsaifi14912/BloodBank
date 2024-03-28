package BloodBank.Services;

import BloodBank.Entity.*;
import BloodBank.Repository.BloodInfoRepository;
import BloodBank.Repository.RequestRepository;
import BloodBank.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.time.ZoneId;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserRequestService {
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BloodInfoRepository bloodInfoRepository;

    public void addRequest(UserRequestDTO userRequestDTO) {
        UserRequestModel userRequestModel = new UserRequestModel();
        userRequestModel.setBloodGroup(userRequestDTO.getBloodGroup());
        userRequestModel.setType(userRequestDTO.getType());
        userRequestModel.setQuantity(userRequestDTO.getQuantity());
        userRequestModel.setCreated_on(LocalDateTime.now());
        userRequestModel.setUpdated_by("ADMIN");
        userRequestModel.setStatus("Pending");
        userRequestModel.setUpdated_on(LocalDateTime.now());
        userRequestModel.setCreated_by(((UserSignUpDetailsDTO) httpSession.getAttribute("loggedInUser")).getCreatedby());
        userRequestModel.setUsername(((UserSignUpDetailsDTO) httpSession.getAttribute("loggedInUser")).getUsername());
        userRequestModel.setUserModel(userRepository.findByUsername(((UserSignUpDetailsDTO) httpSession.getAttribute("loggedInUser")).getUsername()));
        userRequestModel.setRemark("-");
        requestRepository.save(userRequestModel);
    }

    public List<HashMap<String, Object>> getAllRequest() {
        List<UserRequestModel> all = requestRepository.findAll();
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();

        for (UserRequestModel userRequestModel : all) {
            HashMap<String, Object> requestList = new HashMap<>();
            requestList.put("ReqId", userRequestModel.getReqID());
            requestList.put("username", userRequestModel.getUsername());
            requestList.put("createdOn", userRequestModel.getCreated_on());
            requestList.put("createdBy", userRequestModel.getCreated_by());
            requestList.put("status", userRequestModel.getStatus());
            requestList.put("type", userRequestModel.getType());
            requestList.put("bloodGroup", userRequestModel.getBloodGroup());
            java.sql.Date dob=userRequestModel.getUserModel().getDob();
            requestList.put("dob", dob);
            requestList.put("quantity", userRequestModel.getQuantity());

            // Age Calculation
            LocalDate now = LocalDate.now();
            LocalDate birthLocalDate = dob.toLocalDate();
            int age = now.getYear() - birthLocalDate.getYear();
            if (now.getMonthValue() < birthLocalDate.getMonthValue() ||
                    (now.getMonthValue() == birthLocalDate.getMonthValue() &&
                            now.getDayOfMonth() < birthLocalDate.getDayOfMonth())) {
                age--;
            }

            requestList.put("age", age);
            list.add(requestList);
        }
        return list;
    }

    public UserRequestDTO convertToRequestDTO(UserRequestModel userRequestModel) {
        if (userRequestModel != null) {
            return modelMapper.map(userRequestModel, UserRequestDTO.class);
        } else {
            return null;
        }
    }

    public void updateRequestStatus(String status,Long reqId){
        requestRepository.updateStatus(status,reqId);
        requestRepository.DateUpdate(LocalDateTime.now(),reqId);
    }

    public void updateRequestRemark(Long reqId,String remark){
        requestRepository.updateRemark(remark,reqId);
    }

    public List<UserRequestModel> getMyRequest(String username){
        List<UserRequestModel> userRequestModelList=new ArrayList<>();
        List<UserRequestModel> userRequestModelList1=requestRepository.findAll();
        for(UserRequestModel i:userRequestModelList1) {
            if (i.getUsername().equals(username))
                userRequestModelList.add(i);
        }
        return userRequestModelList;
    }

    public List<UserRequestModel> getMyUserRequest(String username){
        List<UserRequestModel> userRequestModelList=new ArrayList<>();
        List<UserRequestModel> userRequestModelList1=requestRepository.findAll();
        for(UserRequestModel i:userRequestModelList1) {
            if (i.getCreated_by().equalsIgnoreCase(username)) {
                userRequestModelList.add(i);
            }
        }
        return userRequestModelList;
    }

    public Boolean canDonate(String username,String bloodGroup){
        List<UserRequestModel> userRequestModelList=getMyRequest(username).stream()
                .filter(u->u.getStatus().equals("accepted"))
                .filter(u->u.getBloodGroup().equalsIgnoreCase(bloodGroup))
                .collect(Collectors.toList());
        if(!userRequestModelList.isEmpty()) {
            userRequestModelList.sort((request1, request2) ->
                    request2.getUpdated_on().compareTo(request1.getUpdated_on()));
            Period period=Period.between(userRequestModelList.get(0).getUpdated_on().toLocalDate(),LocalDate.now());
            return period.getMonths() >= 3;
        }
        return true;
    }
    public List<HashMap<String,String>> requestreport(String username){
        List<UserRequestModel> list = requestRepository.findAll();
        List<UserRequestModel> userRequestModelList;
        if(username.equals("ADMIN"))
            userRequestModelList=list;
        else
            userRequestModelList=list.stream().filter(u->u.getCreated_by().equalsIgnoreCase(username)).collect(Collectors.toList());
        Map<String, Integer> acceptedCounts = new HashMap<>();
        Map<String, Integer> rejectedCounts = new HashMap<>();

        // Count accepted and rejected requests
        for(UserRequestModel request: userRequestModelList) {
            String blood_group = request.getBloodGroup();
            String status = request.getStatus();
            if ("accepted".equals(status))
                acceptedCounts.put(blood_group, acceptedCounts.getOrDefault(blood_group, 0) + 1);
            else if ("rejected".equals(status))
                rejectedCounts.put(blood_group, rejectedCounts.getOrDefault(blood_group, 0) + 1);
        }

        // Collect all blood groups
        Set<String> allBloodGroups = new HashSet<>();
        for (UserRequestModel request: userRequestModelList) {
            allBloodGroups.add(request.getBloodGroup());
        }

        List<HashMap<String,String>> report = new ArrayList<>();
        for (String blood_group : allBloodGroups) {
            HashMap<String,String> map = new HashMap<>();
            map.put("BloodGroup", blood_group);
            map.put("Accepted", String.valueOf(acceptedCounts.getOrDefault(blood_group, 0)));
            map.put("Rejected", String.valueOf(rejectedCounts.getOrDefault(blood_group, 0)));
            int coinValue = calculateCoinValue(blood_group);
            map.put("CoinValue", String.valueOf(coinValue));
            report.add(map);
        }
        return report;
    }

    // Function to calculate coin value based on accepted requests and type of request
    private int calculateCoinValue(String blood_group) {
        List<UserRequestModel> acceptedRequests = requestRepository.findByStatusAndBloodGroup("accepted", blood_group);
        int coinValue = 0;

        for (UserRequestModel userRequestModel : acceptedRequests) {
            BloodInfoModel bloodInfoModel = bloodInfoRepository.findByBloodGroup(blood_group);
            int coinPerUnit = bloodInfoModel.getCoinPerUnit();
            int quantity = userRequestModel.getQuantity();

            // Check request type and adjust coin value accordingly
            if ("Donate".equals(userRequestModel.getType())) {
                coinValue += coinPerUnit * quantity;
            } else if ("Receive".equals(userRequestModel.getType())) {
                coinValue -= coinPerUnit * quantity;
            }
        }
        return coinValue;
    }
    public List<HashMap<String, Object>> getFilterRequests(String filterBy,String startDate, String endDate, String username,String status, List<HashMap<String, Object>> allRequest) {
        switch (filterBy) {
            case "status":
                return allRequest.stream().filter(request -> request.get("status").toString().equalsIgnoreCase(status)).collect(Collectors.toList());
            case "username":
                if (username != null && !username.isEmpty()) {
                return allRequest.stream().filter(request -> request.get("username").toString().startsWith(username)).collect(Collectors.toList());
                }
            case "createdBetween":
                if (startDate != null && endDate != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date sdate = dateFormat.parse(startDate);
                        Date edate = dateFormat.parse(endDate);
                        // Filter the list based on createdAt field
                        return allRequest.stream()
                                .filter(user -> {
                                    LocalDateTime createdAt = (LocalDateTime) user.get("createdOn");
                                    Date createdAtDate = Date.from(createdAt.atZone(ZoneId.systemDefault()).toInstant());
                                    return createdAtDate.compareTo(sdate) >= 0 && createdAtDate.compareTo(edate) <= 0;
                                })
                                .collect(Collectors.toList());
                    } catch (ParseException e) {
                        System.out.println("Invalid date format");
                    }
                }
        }
        return allRequest;
    }

    public List<UserRequestModel> getFilterRequestsAgentUser(String filterBy,String startDate, String endDate, String username,String status, List<UserRequestModel> allRequest) {
        switch (filterBy) {
            case "status":
                return allRequest.stream().filter(request -> request.getStatus().equalsIgnoreCase(status)).collect(Collectors.toList());
            case "username":
                if (username != null && !username.isEmpty()) {
                    return allRequest.stream().filter(request -> request.getUsername().startsWith(username)).collect(Collectors.toList());
                }
            case "createdBetween":
                if (startDate != null && endDate != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date sdate = dateFormat.parse(startDate);
                        Date edate = dateFormat.parse(endDate);
                        // Filter the list based on createdAt field
                        return allRequest.stream()
                                .filter(user -> {
                                    LocalDateTime createdAt = user.getCreated_on();
                                    Date createdAtDate = Date.from(createdAt.atZone(ZoneId.systemDefault()).toInstant());
                                    return createdAtDate.compareTo(sdate) >= 0 && createdAtDate.compareTo(edate) <= 0;
                                })
                                .collect(Collectors.toList());
                    } catch (ParseException e) {
                        System.out.println("Invalid date format");
                    }
                }
        }
        return allRequest;
    }
}
