package utiltracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "UtilityBill")
@Table(name = "utility_bill")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="billId")
public class UtilityBill implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "bill_id")
        private int billId;

        @Column(name = "month")
        private String month;

        @Column(name = "utility_type")
        private String utilityType;

        @Column(name = "amount")
        private BigDecimal amount;

        @Column(name = "user_id")
        private int userId;

        @Column(name = "address_id")
        private int addressId;

        // Default Constructor
        public UtilityBill() {}

        public UtilityBill(String aMonth, String aUtilityType, BigDecimal aAmount, int aUserId, int aAddressId) {
            this.month = aMonth;
            this.utilityType = aUtilityType;
            this.amount = aAmount;
            this.userId = aUserId;
            this.addressId = aAddressId;
        }

        public int getBillId() {
            return billId;
        }

        public void setBillId(int billId) {
            this.billId = billId;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getUtilityType() {
            return utilityType;
        }

        public void setUtilityType(String utilityType) {
            this.utilityType = utilityType;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public int getAddressId() {
            return addressId;
        }

        public void setAddressId(int addressId) {
            this.addressId = addressId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

}
