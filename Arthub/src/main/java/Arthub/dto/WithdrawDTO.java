package Arthub.dto;

import java.time.LocalDateTime;

public class WithdrawDTO {
     private int withdrawID;
        private double coinWithdraw;
        private LocalDateTime dateRequest;
        private int userID;
        private String bankNumber;
        private String bankName;
        private int status;

        public int getWithdrawID() {
            return withdrawID;
        }

        public void setWithdrawID(int withdrawID) {
            this.withdrawID = withdrawID;
        }

        public double getCoinWithdraw() {
            return coinWithdraw;
        }

        public void setCoinWithdraw(double coinWithdraw) {
            this.coinWithdraw = coinWithdraw;
        }

        public LocalDateTime getDateRequest() {
            return dateRequest;
        }

        public void setDateRequest(LocalDateTime dateRequest) {
            this.dateRequest = dateRequest;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public String getBankNumber() {
            return bankNumber;
        }

        public void setBankNumber(String bankNumber) {
            this.bankNumber = bankNumber;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }


}
