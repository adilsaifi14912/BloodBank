//package in.sp.main.util;
//
//import in.sp.main.dto.RegisterDTO;
//
//import java.util.Comparator;
//
//public class UserComparators {
//    public static class UsernameComparator implements Comparator<RegisterDTO> {
//        private final boolean ascending;
//
//        public UsernameComparator(boolean ascending) {
//            this.ascending = ascending;
//        }
//
//        @Override
//        public int compare(RegisterDTO user1, RegisterDTO user2) {
//            int result = user1.getUsername().compareTo(user2.getUsername());
//            return ascending ? result : -result; // Reverse order if descending
//        }
//    }
//
//    public static class CreatedByComparator implements Comparator<RegisterDTO> {
//        private final boolean ascending;
//
//        public CreatedByComparator(boolean ascending) {
//            this.ascending = ascending;
//        }
//
//        @Override
//        public int compare(RegisterDTO user1, RegisterDTO user2) {
//            int result = user1.getCreatedBy().compareTo(user2.getCreatedBy());
//            return ascending ? result : -result; // Reverse order if descending
//        }
//    }
//
//    public static class BloodGroupComparator implements Comparator<RegisterDTO> {
//        private final boolean ascending;
//
//        public BloodGroupComparator(boolean ascending) {
//            this.ascending = ascending;
//        }
//
//        @Override
//        public int compare(RegisterDTO user1, RegisterDTO user2) {
//            int result = user1.getBloodGroup().compareTo(user2.getBloodGroup());
//            return ascending ? result : -result; // Reverse order if descending
//        }
//    }
//}
