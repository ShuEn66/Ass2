package my.edu.tarc.ass2

import androidx.lifecycle.LiveData
import androidx.room.*
import my.tarc.mycontact.*


@Dao
interface databaseDao {

    //BILL
    @Query("SELECT Bill.OverallUsage FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    fun getOverallUsage(accNo: Long, month: Int,  year:Int):LiveData<Bill>

    @Query("SELECT Bill.BillStatus FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    fun getBillStatus(accNo: Long, month: Int,  year:Int):LiveData<Bill>

    @Query("SELECT Bill.DueDate FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    fun getPaymentDue(accNo: Long, month: Int,  year:Int):LiveData<Bill>

    @Query("SELECT Bill.TotalAmount FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    fun getTotalAmount(accNo:Long, month: Int , year:Int):LiveData<Bill>

    //@Query("SELECT * FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo ORDER BY ")
    //suspend fun getBillingHistory(accNo: Long ):LiveData<List<Bill>>

    @Query("SELECT  Bill.CurrentCharges FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    fun getCurrentCharges(accNo: Long,  month: Int,  year:Int):LiveData<Bill>

    @Query("SELECT Bill.OutstandingCharges FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    fun getOutstandingCharges(accNo: Long,  month: Int,  year:Int):LiveData<Bill>

    @Query("SELECT Bill.InvoiceDat FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    fun getInvoiceDate(accNo: Long,  month: Int,  year:Int):LiveData<Bill>

    @Query("SELECT Bill.OverdueCharges FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    fun getOverdueCharges(accNo: Long,  month: Int,  year:Int):LiveData<Bill>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun setBillingDetails(Bill:Bill)

    @Query("UPDATE Bill SET BillStatus = 'Successful' ")
    fun updateBillStatus()


    //USER

    //PAYMENT
    @Query("SELECT Payment.PaymentDate FROM Payment, Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND Payment.PaymentID = Bill.PaymentID AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year ")
    fun getPaymentDate(accNo: Long,  month: Int,  year:Int):LiveData<Payment>

    @Query("SELECT Payment.PaymentStatus FROM Payment, Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND Payment.PaymentID = Bill.PaymentID AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    fun getPaymentStatus(accNo: Long,  month: Int, year:Int):LiveData<Payment>

    @Query("SELECT Payment.PaymentMethod FROM Payment, Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND Payment.PaymentID = Bill.PaymentID AND ElectricityAcc.AccNumber = :accNo OAND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    fun getPaymentMethod(accNo: Long,  month: Int,  year:Int):LiveData<Payment>

    @Query("UPDATE Payment SET PaymentStatus = 'Successful'")
    fun updatePaymentStatus()

    //ELECTRICITYACC
    @Query("SELECT * FROM ElectricityAcc WHERE AccNumber = : accNo")
    fun getAccount(accNo: Long):LiveData<ElectricityAcc>

    //APPLIANCES
    //get appliance details
    @Query("SELECT * FROM Appliances, User WHERE Appliances.UserEmail = User.UserEmail AND User.UserEmail = : userEmail AND Appliances.AppliancesName= : appliancesName")
    fun getAppliancesName(userEmail: String, appliancesName: String ):LiveData<Appliances>

    //get the list of appliances
    @Query("SELECT Appliance.AppliancesType, Appliance.EstimatedUsage FROM Appliances, User WHERE Appliances.UserEmail = User.UserEmail AND User.UserEmail = : userEmail ")
    fun getEstimatedUsage(userEmail: String ):LiveData<List<Appliances>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun setAppliances(Appliances:Appliances)

    @Delete
    fun deleteAppliances(Appliances:Appliances)
}