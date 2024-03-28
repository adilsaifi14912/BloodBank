package com.insightgeeks.bloodbank.util;
import com.insightgeeks.bloodbank.dto.SignupDTO;
import com.insightgeeks.bloodbank.entities.UserModel;
import java.util.Comparator;

public class UserComparators {
    public static class UsernameComparator implements Comparator<SignupDTO> {
        private final boolean ascending;

        public UsernameComparator(boolean ascending) {
            this.ascending = ascending;
        }

        @Override
        public int compare(SignupDTO user1, SignupDTO user2) {
            int result = user1.getUsername().compareTo(user2.getUsername());
            return ascending ? result : -result;
        }
    }

    public static class CreatedByComparator implements Comparator<SignupDTO> {
        private final boolean ascending;

        public CreatedByComparator(boolean ascending) {
            this.ascending = ascending;
        }

        @Override
        public int compare(SignupDTO user1, SignupDTO user2) {
            int result = user1.getCreatedBy().compareTo(user2.getCreatedBy());
            return ascending ? result : -result;
        }
    }

    public static class BloodGroupComparator implements Comparator<SignupDTO> {
        private final boolean ascending;

        public BloodGroupComparator(boolean ascending) {
            this.ascending = ascending;
        }

        @Override
        public int compare(SignupDTO user1, SignupDTO user2) {
            int result = user1.getBloodGroup().compareTo(user2.getBloodGroup());
            return ascending ? result : -result;
        }
    }
}
