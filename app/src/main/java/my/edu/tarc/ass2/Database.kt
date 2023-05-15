package my.edu.tarc.ass2

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "User")
data class User (@PrimaryKey val UserEmail: String, val UserPassword: String,val UserName: String, val UserIC: String, val UserMobile: String, val AccNickName: String, val NoOfResident: Int, val MonthlyIncome: Double, val AccNumber:Long) {
    override fun toString(): String {
        return "$UserEmail :$UserPassword: $UserName: $UserIC: $UserMobile: $AccNickName: $NoOfResident: $MonthlyIncome: $AccNumber"
    }
}

@Entity(tableName = "Payment")
data class Payment (@PrimaryKey val PaymentID: String, val PaymentDate: String, val PaymentStatus: String, val PaymentMethod: String, val PayAmount: Double) {
    override fun toString(): String {
        return "$PaymentID : $PaymentDate:  $PaymentStatus: $PaymentMethod: $PayAmount"
    }
}

@Entity(tableName = "ElectricityAcc")
data class ElectricityAcc (@PrimaryKey val AccNumber:Long,  val AccAddress: String, val AccPropertyType: String,  val AccOwner: String) {
    override fun toString(): String {
        return "$AccNumber : $AccAddress: $AccPropertyType: $AccOwner"
    }
}

@Entity(tableName = "Bill")
data class Bill(@PrimaryKey val BillID: String, val BillingMonth: Int, val BillingYear: Int, val TotalAmount: Double, val InvoiceDate: String, val DueDate: String, val BillStatus: String, val OverallUsage:Double, val CurrentCharges: Double, val OutstandingCharges:Double, val OverDueCharges:Double, val AccNumber: Long, val PaymentID: String) {
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

@Entity(tableName = "CurrentUser")
data class CurrentUser (@PrimaryKey val no:Int, val AccNumber: Long, val currentEmail : String) {
    override fun toString(): String {
        return " $no: $AccNumber:  $currentEmail "
    }
}

