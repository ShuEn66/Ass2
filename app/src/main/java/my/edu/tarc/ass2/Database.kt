package my.tarc.mycontact

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "User")
data class User (@PrimaryKey val UserEmail: String, val UserName: String, val UserAddress: String, val UserIC: Int, val UserMobile: Int, val AccNickName: String, val NoOfResident: Int, val MonthlyIncome: Float, val AccNumber: Int) {
    override fun toString(): String {
        return "$UserEmail : $UserName: $UserAddress: $UserIC: $UserMobile: $AccNickName: $NoOfResident: $MonthlyIncome: $AccNumber"
    }
}

@Entity(tableName = "Payment")
data class Payment (@PrimaryKey val PaymentID: String, val PaymentDate: Date, val PaymentStatus: String, val PaymentMethod: String, val PayAmount: Float) {
    override fun toString(): String {
        return "$PaymentID : $PaymentDate:  $PaymentStatus: $PaymentMethod: $PayAmount"
    }
}

@Entity(tableName = "ElectricityAcc")
data class ElectricityAcc (@PrimaryKey val AccNumber: Int,  val AccAddress: String, val AccPropertyType: String) {
    override fun toString(): String {
        return "$AccNumber : $AccAddress: $AccPropertyType"
    }
}

@Entity(tableName = "Bill")
data class Bill (@PrimaryKey val BillID: String,  val BillingMonth: Int, val TotalAmount: Float, val InvoiceDate: Date, val DueDate: Date, val BillStatus: String, val OverallUsage:Float, val CurrentCharges: Float, val OutstandingCharges: Float, val OverDueCharges:Float, val AccNumber: Int, val PaymentID: String) {
    override fun toString(): String {
        return "$BillID : $BillingMonth: $TotalAmount: $InvoiceDate: $DueDate: $BillStatus: $OverallUsage: $CurrentCharges: $OutstandingCharges : $OverDueCharges: $AccNumber: $PaymentID"
    }
}

@Entity(tableName = "Appliances")
data class Appliances (@PrimaryKey val AppliancesID: String,  val AppliancesName: String, val AppliancesType : String, val EstimatedUsage:Float, val AppliancesPower:Float) {
    override fun toString(): String {
        return " $AppliancesName:  $AppliancesType: $EstimatedUsage: $AppliancesPower "
    }
}

