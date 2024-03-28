    package in.sp.main.controllers;

    import in.sp.main.dto.BloodBankDTO;
    import in.sp.main.dto.RegisterDTO;
    import in.sp.main.service.*;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestParam;

    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpSession;
    import java.util.List;

    @Controller
    public class BloodController {

        @Autowired
        LoginServiceImpl loginService;

        @Autowired
        DatabaseSetupService databaseSetupService;

        @Autowired
        RegisterServiceImpl registerService;

        @Autowired
        RegisterDTO user;

        @Autowired
        AdminService adminService;

        @Autowired
        BloodBankServiceImpl bloodBankService;

        @PostMapping("/bloodRequest")
        public String handleBloodRequest(@ModelAttribute BloodBankDTO bloodBankDTO,
                                         HttpServletRequest httpServletRequest, Model mp)
        {
            HttpSession session = httpServletRequest.getSession();
            RegisterDTO userDTO = (RegisterDTO)session.getAttribute("user");
            boolean b = bloodBankService.canDonateBlood(userDTO.getUsername());
            if(bloodBankDTO.getRequestType().equalsIgnoreCase("donation") && !b) {
//                System.out.println("you r not eligible");
                mp.addAttribute("msg", "You have already requested");
                return "bloodRequest-form";
            }
            bloodBankService.bloodBankService(bloodBankDTO,userDTO);
            mp.addAttribute("msg", "request done");

            return "bloodRequest-form";
        }


            @GetMapping("/donateAndReceive")
            public String createRequest ()
            {
                return "bloodRequest-form";
            }


        @GetMapping("/bloodStock")
        public String adminDashboard(Model model) {
            // Get blood stock from the service
            model.addAttribute("bloodRequests",adminService.fetchBloodRequests());
            return "blood-stock";
        }

        @GetMapping("/approveBloodRequest")
        public String updateCoin(@RequestParam String requestId ,@RequestParam String coins,Model model) {
            Long id= Long.valueOf(requestId);
            int   coin = Integer.parseInt(coins);
//            bloodBankService.updateCoins(id,coin);

            List<BloodBankDTO> bloodBankDTOList=adminService.fetchBloodRequest();
            model.addAttribute("bloodRequests",adminService.fetchBloodRequests());
            model.addAttribute("agentUser",loginService.fetchAgentData());
            model.addAttribute("bloodBankDTOList",bloodBankDTOList);

            bloodBankService.updateStatus(id,"Accepted");
            return "bloodRequests";
        }

        @GetMapping("/rejectBloodRequest")
        public String rejectedBloodRequest(@RequestParam String requestId,Model model)
        {
         Long id=Long.valueOf(requestId);
         List<BloodBankDTO>bloodBankDTOList=adminService.fetchBloodRequest();
         model.addAttribute("bloodRequests",adminService.fetchBloodRequests());
         model.addAttribute("agentUser",loginService.fetchAgentData());
         model.addAttribute("bloodBankDTOList",bloodBankDTOList);
         bloodBankService.updateStatus(id,"Rejected");
         return "bloodRequests";
        }


        @GetMapping("/bloodStatus")
        public String getUserBloodRequestUserDash( Model model, HttpServletRequest httpServletRequest)
        {
            HttpSession session = httpServletRequest.getSession();
            RegisterDTO user = (RegisterDTO) session.getAttribute("user");
            model.addAttribute("bloodRequestByUser",bloodBankService.getBloodRequestUserDash(user.getUsername()));
            System.out.println("////////////////////////////////////////");
            System.out.println(bloodBankService.getBloodRequestUserDash(user.getUsername()));
            return "user-bloodRequest-status";
        }

    }
