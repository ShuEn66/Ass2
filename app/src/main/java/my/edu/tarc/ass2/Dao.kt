package my.edu.tarc.ass2

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface databaseDao {


    //BILL
    @Query("SELECT OverallUsage FROM Bill WHERE AccNumber = :accNo AND BillingMonth = :month AND BillingYear =  :year")
    suspend fun getOverallUsage(accNo: Long, month: Int,  year:Int): Double

    @Query("SELECT BillStatus FROM Bill WHERE AccNumber = :accNo AND BillingMonth = :month AND BillingYear =  :year")
    suspend fun getBillStatus(accNo: Long, month: Int,  year:Int): String

    @Query("SELECT DueDate FROM Bill WHERE AccNumber = :accNo AND BillingMonth = :month AND BillingYear =  :year")
    suspend fun getPaymentDue(accNo: Long, month: Int,  year:Int):String

    @Query("SELECT TotalAmount FROM Bill WHERE AccNumber = :accNo AND BillingMonth = :month AND BillingYear =  :year")
    suspend fun getTotalAmount(accNo:Long, month: Int , year:Int): Double

    //@Query("SELECT * FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo ORDER BY ")
    //suspend fun getBillingHistory(accNo: Long ):LiveData<List<Bill>>

    @Query("SELECT CurrentCharges FROM Bill WHERE AccNumber = :accNo AND BillingMonth = :month AND BillingYear =  :year")
    suspend fun getCurrentCharges(accNo: Long,  month: Int,  year:Int): Double

    @Query("SELECT OutstandingCharges FROM Bill WHERE AccNumber = :accNo AND BillingMonth = :month AND BillingYear =  :year")
    suspend fun getOutstandingCharges(accNo: Long,  month: Int,  year:Int):Double

    @Query("SELECT InvoiceDate FROM Bill WHERE AccNumber = :accNo AND BillingMonth = :month AND BillingYear =  :year")
    suspend fun getInvoiceDate(accNo: Long,  month: Int,  year:Int): String

    @Query("SELECT OverdueCharges FROM Bill WHERE AccNumber = :accNo AND BillingMonth = :month AND BillingYear =  :year")
    suspend fun getOverdueCharges(accNo: Long,  month: Int,  year:Int):Double

    @Query("SELECT BillID FROM Bill WHERE AccNumber = :accNo AND BillingMonth = :month AND BillingYear =  :year")
    suspend fun getBillID(accNo: Long,  month: Int,  year:Int):String

    @Query("SELECT BillingMonth FROM Bill WHERE AccNumber = :accNo ")
    suspend fun getBarMonth(accNo: Long):List<Int>

    @Query("SELECT OverallUsage FROM Bill WHERE AccNumber = :accNo ")
    suspend fun getBarOverallUsage(accNo: Long):List<Double>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setBillingDetails(Bill:Bill)

    @Query("UPDATE Bill SET BillStatus = 'Paid' WHERE BillID = :billId")
    suspend fun updateBillStatus(billId: String)

    @Query("UPDATE Bill SET CurrentCharges = 0.0")
    suspend fun updateCurrentChanges()

    @Query("UPDATE Bill SET OutstandingCharges = 0.0")
    suspend fun updateOutstandingChanges()

    @Query("UPDATE Bill SET TotalAmount = 0.0")
    suspend fun updateTotalAmount()

    @Query("SELECT * FROM Bill WHERE AccNumber = :accNo")
    fun getAllBill(accNo: Long):LiveData<List<Bill>>

    //USER
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setUserDetails(User:User)

    @Query("UPDATE User SET UserName = :newName WHERE UserEmail =:email ")
    suspend fun updateUserName(email:String,newName:String)

    @Query("UPDATE User SET UserIC = :newIC WHERE UserEmail =:email ")
    suspend fun updateUserIC(email:String,newIC:String)

    @Query("UPDATE User SET UserMobile = :newMobile WHERE UserEmail =:email ")
    suspend fun updateUserMobile(email:String,newMobile:String)

    @Query("UPDATE User SET UserPassword = :newPassword WHERE UserEmail =:email ")
    suspend fun updateUserPassword(email:String,newPassword:String)

    @Query("UPDATE User SET AccNickName = :newNickName WHERE UserEmail =:email ")
    suspend fun updateAccName(email:String,newNickName:String)

    @Query("UPDATE User SET AccNumber = :newAccNumber WHERE UserEmail =:email ")
    suspend fun updateAccNum(email:String,newAccNumber:Long)

    @Query("UPDATE User SET NoOfResident = :newNo WHERE UserEmail =:email ")
    suspend fun updateNoResident(email:String,newNo:Int)

    @Query("UPDATE User SET MonthlyIncome = :newIncome WHERE UserEmail =:email ")
    suspend fun updateMonthlyIncome(email:String,newIncome:Double)

    @Query("SELECT * FROM User WHERE UserEmail = :email")
    suspend fun getUserbyEmail(email: String): User

    @Query("SELECT UserEmail FROM User WHERE AccNumber = :accNo")
    suspend fun getUserEmail(accNo: Long): String

    @Query("SELECT UserPassword FROM User WHERE UserEmail = :email")
    suspend fun getUserPassword(email: String): String

    @Query("SELECT UserName FROM User WHERE UserEmail = :email")
    suspend fun getUserName(email: String): String

    @Query("SELECT UserIC FROM User WHERE UserEmail = :email")
    suspend fun getUserIC(email: String): String

    @Query("SELECT UserMobile FROM User WHERE UserEmail = :email")
    suspend fun getUserMobile(email: String): String

    @Query("SELECT AccNickName FROM User WHERE UserEmail = :email")
    suspend fun getAccNickName(email: String): String

    @Query("SELECT NoOfResident FROM User WHERE UserEmail = :email")
    suspend fun getNoResident(email: String): Int

    @Query("SELECT MonthlyIncome FROM User WHERE UserEmail = :email")
    suspend fun getMonthlyIncome(email: String): Double

    @Query("SELECT AccNumber FROM User WHERE UserEmail = :email")
    suspend fun getAccNumber(email: String): Long

    @Query("SELECT * FROM User WHERE AccNumber = :accNo")
    suspend fun getUser(accNo: Long): User

    @Query("DELETE FROM User WHERE UserEmail = :email")
    suspend fun deleteUser(email: String)


    //PAYMENT
    @Query("SELECT PaymentDate FROM Payment WHERE PaymentID = :paymentId")
    suspend fun getPaymentDate(paymentId: Long):String

    @Query("SELECT PaymentStatus FROM Payment WHERE PaymentID = :paymentId")
    suspend fun getPaymentStatus(paymentId: Long): String

    @Query("SELECT PaymentMethod FROM Payment WHERE PaymentID = :paymentId")
    suspend fun getPaymentMethod(paymentId: Long): String

    @Query("SELECT PayAmount FROM Payment WHERE PaymentID = :paymentId")
    suspend fun getPayAmount(paymentId: Long): Double

    @Query("UPDATE Payment SET PaymentStatus = 'Paid'")
    suspend fun updatePaymentStatus()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setPaymentDetails(Payment:Payment)

    //ELECTRICITYACC

    @Query("SELECT AccPropertyType FROM ElectricityAcc WHERE AccNumber = :accNo")
    suspend fun getAccountProperty(accNo: Long):String

    @Query("SELECT AccAddress FROM ElectricityAcc WHERE AccNumber = :accNo")
    suspend fun getAccountAddress(accNo: Long):String

    @Query("SELECT * FROM ElectricityAcc WHERE AccNumber = :accNo")
    suspend fun getAccount(accNo: Long):ElectricityAcc

    @Query("SELECT AccOwner FROM ElectricityAcc WHERE AccNumber = :accNo")
    suspend fun getAccountOwner(accNo: Long):String


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setAccDetails(ElectricityAcc:ElectricityAcc)



    //APPLIANCES

    @Query("SELECT * FROM Appliances WHERE UserEmail =:userEmail AND AppliancesName =:appliancesName")
    fun getAppliances(userEmail: String, appliancesName: String ):LiveData<Appliances>

    @Query("SELECT * FROM Appliances WHERE UserEmail =:userEmail")
    fun getAllAppliances(userEmail: String):LiveData<List<Appliances>>

    @Query("SELECT EstimatedUsage FROM Appliances WHERE UserEmail =:userEmail AND AppliancesName =:appliancesName")
    suspend fun getEstimatedUsage(userEmail: String, appliancesName: String ):Double

    @Query("SELECT AppliancesPower FROM Appliances WHERE UserEmail =:userEmail AND AppliancesName =:appliancesName")
    suspend fun getAppliancesPower(userEmail: String, appliancesName: String ):Double

    @Query("SELECT AppliancesName FROM Appliances WHERE UserEmail =:userEmail")
    suspend fun getAppliancesName(userEmail: String ):String

    @Query("SELECT AppliancesType FROM Appliances WHERE UserEmail =:userEmail AND AppliancesName =:appliancesName")
    suspend fun getAppliancesType(userEmail: String, appliancesName: String ):String

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setAppliances(Appliances:Appliances)

    @Query("DELETE FROM Appliances WHERE AppliancesName =:appliancesName")
    suspend fun deleteAppliances(appliancesName: String)

    //Setting attributes 1 by 1
    @Query("UPDATE Appliances SET AppliancesName =:appliancesName")
    suspend fun setAppName(appliancesName: String)

    @Query("UPDATE Appliances SET AppliancesType =:appliancesType")
    suspend fun setAppType(appliancesType: String)

    @Query("UPDATE Appliances SET EstimatedUsage =:estimatedUsage")
    suspend fun setEstimatedUsage(estimatedUsage: Double)

    @Query("UPDATE Appliances SET AppliancesPower =:appPower")
    suspend fun setAppPower(appPower: Double)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun InitializeCurrent(CurrentUser:CurrentUser)

    @Query("UPDATE CurrentUser SET AccNumber =:accNo AND currentEmail =:email WHERE no=1" )
    suspend fun setCurrentUser(accNo: Long,email: String)

    @Query("UPDATE CurrentUser SET currentEmail =:email WHERE no=1" )
    suspend fun setCurrentEmail(email: String)

    @Query("SELECT * FROM CurrentUser WHERE no=1")
    suspend fun getCurrentUser():CurrentUser

    @Query("SELECT currentEmail FROM CurrentUser WHERE no=1")
    suspend fun getCurrentEmail():String
}