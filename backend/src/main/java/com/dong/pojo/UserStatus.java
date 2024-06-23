package com.dong.pojo;

public enum UserStatus {
    ENABLED {
        @Override
        public String toString() {
            return "Hoạt động";
        }
    },
    DISABLED {
        @Override
        public String toString() {
            return "Vô hiệu hóa";
        }
    },
    PENDING {
        @Override
        public String toString() {
            return "Chờ duyệt";
        }
    },
    NEED_INFO {
        @Override
        public String toString() {
            return "Cần thông tin";
        }
    }
}