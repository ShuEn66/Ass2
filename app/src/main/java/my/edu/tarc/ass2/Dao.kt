package my.edu.tarc.ass2

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface databaseDao {

    //BILL
    @Query("SELECT Bill.OverallUsage FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    suspend fun getOverallUsage(accNo: Long, month: Int,  year:Int): Double

    @Query("SELECT Bill.BillStatus FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    suspend fun getBillStatus(accNo: Long, month: Int,  year:Int): String

    @Query("SELECT Bill.DueDate FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    suspend fun getPaymentDue(accNo: Long, month: Int,  year:Int):String

    @Query("SELECT Bill.TotalAmount FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    suspend fun getTotalAmount(accNo:Long, month: Int , year:Int): Double

    //@Query("SELECT * FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo ORDER BY ")
    //suspend fun getBillingHistory(accNo: Long ):LiveData<List<Bill>>

    @Query("SELECT  Bill.CurrentCharges FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    suspend fun getCurrentCharges(accNo: Long,  month: Int,  year:Int): Double

    @Query("SELECT Bill.OutstandingCharges FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    suspend fun getOutstandingCharges(accNo: Long,  month: Int,  year:Int):Double

    @Query("SELECT Bill.InvoiceDate FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    suspend fun getInvoiceDate(accNo: Long,  month: Int,  year:Int): String

    @Query("SELECT Bill.OverdueCharges FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    suspend fun getOverdueCharges(accNo: Long,  month: Int,  year:Int):Double

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setBillingDetails(Bill:Bill)

    @Query("UPDATE Bill SET BillStatus = 'Successful' ")
    suspend fun updateBillStatus()


    //USER

    //PAYMENT
    @Query("SELECT Payment.PaymentDate FROM Payment, Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND Payment.PaymentID = Bill.PaymentID AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year ")
    suspend fun getPaymentDate(accNo: Long,  month: Int,  year:Int):String

    @Query("SELECT Payment.PaymentStatus FROM Payment, Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND Payment.PaymentID = Bill.PaymentID AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    suspend fun getPaymentStatus(accNo: Long,  month: Int, year:Int): String

    @Query("SELECT Payment.PaymentMethod FROM Payment, Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND Payment.PaymentID = Bill.PaymentID AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    suspend fun getPaymentMethod(accNo: Long,  month: Int,  year:Int): String

    @Query("UPDATE Payment SET PaymentStatus = 'Successful'")
    suspend fun updatePaymentStatus()

    //ELECTRICITYACC
    @Query("SELECT * FROM ElectricityAcc WHERE AccNumber =:accNo")
    fun getAccount(accNo: Long):LiveData<List<ElectricityAcc>>

    //APPLIANCES
    @Query("SELECT * FROM Appliances, User WHERE Appliances.UserEmail = User.UserEmail AND User.UserEmail =:userEmail AND Appliances.AppliancesName= :appliancesName")
    fun getAppliances(userEmail: String, appliancesName: String ):LiveData<Appliances>

    @Query("SELECT Appliances.EstimatedUsage FROM Appliances, User WHERE Appliances.UserEmail = User.UserEmail AND User.UserEmail = :userEmail ")
    suspend fun getEstimatedUsage(userEmail: String ):Double

    @Query("SELECT Appliances.AppliancesName FROM Appliances, User WHERE Appliances.UserEmail = User.UserEmail AND User.UserEmail = :userEmail ")
    suspend fun getAppliancesName(userEmail: String ):String

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setAppliances(Appliances:Appliances)

    @Delete
    suspend fun deleteAppliances(Appliances:Appliances)
}