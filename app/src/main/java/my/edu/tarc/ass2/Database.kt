package my.edu.tarc.ass2

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "User")
data class User (@PrimaryKey val UserEmail: String, val UserName: String, val UserAddress: String, val UserIC: Long, val UserMobile: Long, val AccNickName: String, val NoOfResident: Int, val MonthlyIncome: Double, val AccNumber:Long) {
    override fun toString(): String {
        return "$UserEmail : $UserName: $UserAddress: $UserIC: $UserMobile: $AccNickName: $NoOfResident: $MonthlyIncome: $AccNumber"
    }
}

@Entity(tableName = "Payment")
data class Payment (@PrimaryKey val PaymentID: String, val PaymentDate: Date, val PaymentStatus: String, val PaymentMethod: String, val PayAmount: Double) {
    override fun toString(): String {
        return "$PaymentID : $PaymentDate:  $PaymentStatus: $PaymentMethod: $PayAmount"
    }
}

@Entity(tableName = "ElectricityAcc")
data class ElectricityAcc (@PrimaryKey val AccNumber:Long,  val AccAddress: String, val AccPropertyType: String) {
    override fun toString(): String {
        return "$AccNumber : $AccAddress: $AccPropertyType"
    }
}

@Entity(tableName = "Bill")
data class Bill(@PrimaryKey val BillID: String, val BillingMonth: Int, val BillingYear: Int, val TotalAmount: Double, val InvoiceDate: Date, val DueDate: Date, val BillStatus: String, val OverallUsage:Double, val CurrentCharges: Double, val OutstandingCharges:Double, val OverDueCharges:Double, val AccNumber: Long, val PaymentID: String) {
    override fun toString(): String {
        return "$BillID : $BillingMonth: $BillingYear: $TotalAmount: $InvoiceDate: $DueDate: $BillStatus: $OverallUsage: $CurrentCharges: $OutstandingCharges : $OverDueCharges: $AccNumber: $PaymentID"
    }
}

@Entity(tableName = "Appliances")
data class Appliances (@PrimaryKey val AppliancesName: String, val AppliancesType : String, val EstimatedUsage:Double, val AppliancesPower:Double, val UserEmail: String) {
    override fun toString(): String {
        return " $AppliancesName:  $AppliancesType: $EstimatedUsage: $AppliancesPower "
    }
}

