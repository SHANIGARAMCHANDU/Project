
import java.util.*;
import java.util.regex.Pattern;
public class AN{
   static java.util.Scanner sc=new java.util.Scanner(System.in);
   public static final String Reset="\u001B[0m";
	public static final String Red="\u001B[31m";
	public static final String Yellow="\u001B[33m";
	public static final String Green="\u001B[32m";
	public static final String Purple="\u001B[35m";
	public static final String Cyan="\u001B[36m";
	public static final String Blue="\u001B[34m";
	public static final String BYellow="\u001B[43m";
	public static final String BRed="\u001B[41m";
	public static final String Bsky="\u001B[106m";
	public static final String Blink="\u001B[5m";
  public static ArrayList<User> details=new ArrayList<User>();
  public static ArrayList<Ownerlist> Odetails=new ArrayList<Ownerlist>();
  public static ArrayList<Property> properties = new ArrayList<Property>();
  
  private static final String USERNAME_REGEX = "^[a-zA-Z0-9]([a-zA-Z0-9_]){3,18}[a-zA-Z0-9]$";
  private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
  private static final String PASSWORD_REGEX = 
            "^(?=.*[0-9])" +         // at least one digit
            "(?=.*[a-z])" +          // at least one lowercase letter
            "(?=.*[A-Z])" +          // at least one uppercase letter
            "(?=.*[@#$%^&+=])" +     // at least one special character
            "(?=\\S+$).{8,}$";       // no whitespace, at least 8 characters

    private static final String MOBILE_NUMBER_REGEX = "^[6-9]\\d{9}$";


    public static boolean isValidUsername(String username) {
        return Pattern.matches(USERNAME_REGEX, username);
    }
  

    public static boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
            
    }
    
    public static boolean isValidPassword(String password) {
        return Pattern.matches(PASSWORD_REGEX, password);
    }
    
    public static boolean isValidMobileNumber(String mobileNumber) {
        return Pattern.matches(MOBILE_NUMBER_REGEX, mobileNumber);
    }
 
  
  
  public static void register(){
      
      System.out.println(Red+"\t-------------------------------------------------------------\n"+Reset);
      System.out.println("  \t\t        Register page");
      System.out.println();
      System.out.println(Red+"\t-------------------------------------------------------------\n"+Reset);
      System.out.print(Green+"\tEnter your user name:  "+Reset);
      String username=sc.next();
      
      System.out.print(Cyan+"\tEnter your email id:  "+Reset);
      String emailid=sc.next();
      
      while(!isValidEmail(emailid)){
         System.out.println(Red+"\tIt must contain atleast one uppercase,lowercase letters,numbers"+Reset);
         emailid=sc.next();
      }
      
      System.out.print(Yellow+"\tEnter Password:   "+Reset);
      String password=sc.next();

      while(!isValidPassword(password)){
         System.out.println(Purple+"\tIt must contain no space,atleast 8 characters,one uppercase letter ,lowercase letter,numbers and special character"+Reset);
         password=sc.next();
      }
      System.out.print(Yellow+"\tEnter Confirm Password:    "+Reset);
      String confirmpassword=sc.next();
      while(!password.equals(confirmpassword)){
         System.out.println(Blue+"\t\tPassword doesn't match "+Reset);
         confirmpassword=sc.next();
      }
      
      System.out.print(Red+"\tEnter your mobile number:   "+Reset);
      String mobileno=sc.next();
      while(!isValidMobileNumber(mobileno)){
         System.out.println(Cyan+"\tEnter valid mobile number "+Reset);
         mobileno=sc.next();
      }

      details.add(new User(username,emailid,password,mobileno));
      
      otpgenerate();
      
  }

public static void otpgenerate()
{
       Random r=new Random();
       int otp=1000+r.nextInt(9999-1000+1);
       System.out.println(Purple+"\tYour OTP is: "+otp+Reset);
       System.out.print(Blue+"\tEnter your OTP: "+Reset);
       int OTP=sc.nextInt();
       if(otp==OTP)
       {
          System.out.println();
          System.out.println(Green+"\t\t\tRegistered successfully\n"+Reset);
         home(); 
       }
       else
      {
          System.out.println(Yellow+"\t\tEntered wrong otp.Please try again"+Reset);
          otpgenerate();
      }
  }
static User q=new User();
  public static void login(){

          System.out.println();
          System.out.println(Purple+"  \t\t          Login page   "+Reset);
          System.out.println();
          System.out.println(Red+"\t-------------------------------------------------------\n"+Reset);
          System.out.print(Green+"\tEnter your user name or emailid: "+Reset);
          String s=sc.next();
          System.out.print(Blue+"\tEnter your password: "+Reset);
          String password=sc.next();
          
         if(details.isEmpty())
         {
            System.out.println(Cyan+"\tuser doesn't have an account and need to register "+Reset);
            System.out.println(Blue+"\t1)Register   \n \t2)Go To Home"+Reset);
            int a=sc.nextInt();
            if(a==1)
            {
               register();
            }
            else if(a==2){
               home();
            }
         }
          for(User us:details)
          {
             
            if((us.getUserName().equals(s)||us.getEmailId().equals(s)) && us.getPassword().equals(password)){
                  System.out.println(Purple+"\t\tLogin successfully  "+Reset);
                  q=us;
                  Afterlogin();
                  break;
            }
            else
            {
               System.out.println(Red+"\t\tInvalid username or password ."+Reset);
               login();
            }
         }
   }
           public static void Afterlogin()
           {
               System.out.println(Green+"1. Search Locations 2. Profile 3. Logout"+Reset);
               int x=AN.sc.nextInt();
               if(x==1)
               {
                  Renter.searchLocation();
               }
               else if(x==2)
               {
                  User.Profile();
               }
               else
               {
                  home();
               }
           }
            static void exit()
            {
               System.out.println(Purple+"\t\tExit from the website."+Reset);
            }
            public static void home(){
               System.out.println(Red+"1.Register as Renter"+Reset+Blue+"\n2.Login to Renter"+Reset+Green+"\n3.Login to Owner"+Reset+Yellow+"\n4.Login to Admin "+Reset+Purple+"\n5. Exit"+Reset);
               System.out.print("Select an option :  ");
               int op=sc.nextInt();
               if(op==1)
               {
                  register();
               }
               else if(op==2)
               {
                  login();
               } 
               else if(op==3)
               {
                  Owner1.Ownerlogin();
               }
               else if(op==4)
               {
                  Admin1.loginadmin();
               }
               else
               {
                  exit();
               }
            } 
             
            public static void main(String[] args){
               System.out.println(BYellow+Red+"\t\t\t\tWelcome to Rental Housing "+Reset);
               B.chandu();
               properties.add(new Property("maniKanta ", "kphb", "PG", "4 Beds, includes 3 meals daily, Wi-Fi, laundry services", 7000));
               properties.add(new Property("Skyline_Heights", "kphb", "2BHK Apartment", "High-rise with city views, pool, gym access",15000));
               properties.add(new Property("Villa", "Madhapur", "3BHK House", "Private garden, garage, pet-friendly, close to schools and parks",30000));
               properties.add(new Property("Ocean_Apartments", "madhapur", "1BHK Apartment", "Security, water facility, fully furnished",2000));
               properties.add(new Property("Aarush", "Ameerpet", "PG", "6 Beds, Ladies-only, daily cleaning, 24-hour security, air-conditioned",6500));
               properties.add(new Property("Lakeview_Bungalow", "Ameerpet", "4BHK House", " private dock, large backyard, family-friendly",20000));
               properties.add(new Property("Urban ", "Hitech city", "Studio Apartment", "Ideal for professionals, gym and co-working space included",30000));
               properties.add(new Property("Country", "hitech city", "2BHK House", "Rustic style, garden, play area, parking",25000));
               properties.add(new Property("marie_gold", "kphb", "PG", "4 Beds, includes 3 meals daily, Wi-Fi, laundry services",20000));
               properties.add(new Property("aditya_Heights", "kphb", "2BHK Apartment", "High-rise with city views, pool, gym access",15000));
               properties.add(new Property("aparna_Villa", "Madhapur", "3BHK House", "Private garden, garage, pet-friendly, close to schools and parks",30000));
               properties.add(new Property("manjeera_Apartments", "madhapur", "1BHK Apartment", "Security, water facility, fully furnished",15000));
               properties.add(new Property("sujith_PG", "Ameerpet", "PG", "6 Beds, Ladies-only, daily cleaning, 24-hour security, air-conditioned",6000));
               properties.add(new Property("Bungalow", "Ameerpet", "4BHK House", " private dock, large backyard, family-friendly",30000));
               properties.add(new Property("suhas", "Hitech city", "Studio Apartment", "Ideal for professionals, gym and co-working space included",40000));
               properties.add(new Property("sri", "hitech city", "2BHK House", "Rustic style, garden, play area, parking",25000));
               home();
            }
         }

class Admin{
    private String username="Surendra";
    private String emailid="Vsure12@gmail.com";
    private String password="Vsure123";
        
    String getUserName(){
        return username;
    }
    String getEmailId(){
        return emailid;
    }
    String getPassword(){
        return password;
    }
             
}
class Admin1{
    public static final String Reset="\u001B[0m";
    public static final String Red="\u001B[31m";
    public static final String Yellow="\u001B[33m";
    public static final String Green="\u001B[32m";
    public static final String Purple="\u001B[35m";
    public static final String Cyan="\u001B[36m";
    public static final String Blue="\u001B[34m";
    public static void loginadmin()
    {
        System.out.println(Blue+" \t\tAdmin Login Page"+Reset);
        System.out.println(Red+"\t ----------------------------\n\n"+Reset);
        System.out.print(Green+"\tEnter Username : "+Reset);
        String adun=AN.sc.next();
        System.out.print(Cyan+"\n\tEnter Password : "+Reset);
        String adpas=AN.sc.next();
        Admin ad=new Admin();
        if(ad.getUserName().equals(adun) && ad.getPassword().equals(adpas))
        {
            Afteradminlogin();
        }
        else
        {
            System.out.println(Red+"\tInvalid Username or Password"+Reset);
            System.out.println(Cyan+"\t1.Try again\n\t2.Home"+Reset);
            int a=AN.sc.nextInt();
            if(a==1)
            {
                loginadmin();
            }
            else
            {
                AN.home();
            }
        }
    }
    public static void Afteradminlogin()
   {
        System.out.println(Blue+"\t1.register to new Owner\n\t2.Details of Owner\n\t3.Details of Renter\n\t4.Logout"+Reset);
        int r=AN.sc.nextInt();
        if(r==1)
        {
            Owner1.Ownerregister();
        }
        else if(r==2)
        {
            int c=1;
            for(Ownerlist us:AN.Odetails){
                System.out.println(c+" " +"User Name : "+us.getUserName()+ " "+"Email Id : " +us.getEmailId()+ " " +"Mobile No : "+us.getMobileNo()); 
                c++;
            }
            Afteradminlogin();
        }
        else if(r==3)
        {
            int d=1;
            for(User us:AN.details){
                System.out.println(d+" "+"User Name : " +us.getUserName()+ " "+"Email Id : " +us.getEmailId()+ " "+"Mobile No : "+us.getMobileNo()); 
                d++;
            }
            Afteradminlogin();
        }
        else
        {
            AN.home();
        }
    }
 }

class Renter {
 static Scanner sc = new Scanner(System.in);
 public static final String Reset="\u001B[0m";
	public static final String Red="\u001B[31m";
	public static final String Yellow="\u001B[33m";
	public static final String Green="\u001B[32m";
	public static final String Purple="\u001B[35m";
	public static final String Cyan="\u001B[36m";
	public static final String Blue="\u001B[34m";
 public static void searchLocation() {
                 
     System.out.println(Blue+"1)Location"+Reset+Green+"\n 2)Property type"+Reset+Purple+"\n 3)Both location and property type"+Reset);
     int c=sc.nextInt();
       if(c==1)
      {
       System.out.print(Cyan+"\tEnter Location: "+Reset);
       String location = sc.next();
       System.out.println(Yellow+"\tAvailable properties in " + location + ":"+Reset);
       Owner.searchPropertiesByLocation(location);
       select();
      }
      else if(c==2)
      {
        System.out.print(Red+"\tEnter Property type: "+Reset);
        String type = sc.next();
        System.out.println(Purple+"\tAvailable properties in " + type + ":"+Reset);
        Owner.searchPropertiesByProperty(type);
        select();
       }
        else 
       {
           System.out.print(Cyan+"\tEnter Location: "+Reset);
           String location = sc.next();
           System.out.print(Green+"\tEnter Property type: "+Reset);
           String type = sc.next();
           System.out.println(Blue+"\tAvailable properties in "+Reset+ location +Purple+" and "+Reset+type+ ":"+Reset);
           Owner.searchPropertiesByLocationandProperty(location,type);
             select();
        }
         
    }
     static float amt;
     static String nam;
     public static void select()
    {
       System.out.print(Yellow+"\tEnter a property name to select: "+Reset);
       String name=sc.next();
       boolean found = false;
       for (Property pro : AN.properties) {
       if (pro.getPropertyName().equalsIgnoreCase(name)) {
             System.out.println(pro);
             amt=pro.getAmount();
             nam=pro.getPropertyName();
             test obj=new test();
             obj.p();
             found = true;
             break;
            }
         }
      if (!found) {
         System.out.println(Red+"No properties found for the Property name: " + name+Reset);
         AN.Afterlogin();
        }
      }
    }
 
class User{
      public static final String Reset="\u001B[0m";
	public static final String Red="\u001B[31m";
	public static final String Yellow="\u001B[33m";
	public static final String Green="\u001B[32m";
	public static final String Purple="\u001B[35m";
	public static final String Cyan="\u001B[36m";
	public static final String Blue="\u001B[34m";
    private String username;
    private String emailid;
    private String password;
    private String mobileno;
      User()
      {

      }
      User(String username,String emailid,String password,String mobileno)
      {
            this.username=username;
            this.emailid=emailid;
            this.password=password;
            this.mobileno=mobileno;
            
      }
      void setUsername(String username)
      {
            this.username=username;
      }
      void setEmailId(String emailid)
      {
          this.emailid=emailid;
      }
      void setMobileNo(String mobileno)
      {
            this.mobileno=mobileno;
      }
      void setPassword(String password)
      {
            this.password=password;
      }
      String getUserName()
      {
            return username;
      }
      String getEmailId()
      {
            return emailid;
      }
      String getPassword()
      {
            return password;
      }
      String getMobileNo()
      {
            return mobileno;
      }
      static User o=new User();
      public  static void Profile()
      {     
          System.out.println(Green+"\tUser Name : "+ AN.q.getUserName()+Reset);
          System.out.println(Blue+"\tEmail Id : "+ AN.q.getEmailId()+Reset);
          System.out.println(Yellow+"\tMobil no : " + AN.q.getMobileNo()+Reset);
          System.out.println(Purple+"1. Go To Your Page \n2. Modify Your Details \n3. Logout"+Reset);
          int y=AN.sc.nextInt();
          if(y==1)
          {
            AN.Afterlogin();
          }
          else if(y==2)
          {
            Modify();
          }
          else
          {
            AN.home();
          }
      }
      public static void Modify()
      {
            System.out.println(Cyan+"\tSelect an option to modify your details "+Reset);
            System.out.println(Red+"1. User name \n2. EmailId \n3. Password \n4. Mobile no"+Reset);
            int z=AN.sc.nextInt();
            if(z==1)
            {
                  String U=AN.sc.next();
                  o.setUsername(U);
                  System.out.println(Yellow+"\tYour User Name is Updated "+Reset);
                  User.Profile();
            }
            else if(z==2)
            {
                  String E=AN.sc.next();
                  o.setEmailId(E);
                  System.out.println(Green+"\tYour Email Id is Updated "+Reset);
                  User.Profile();
            }
            else if(z==3)
            {
                  String P=AN.sc.next();
                  o.setPassword(P);
                  System.out.println(Blue+"\tYour password is Updated "+Reset);
                  User.Profile();
            }
            else
            {
                  String M=AN.sc.next();
                  o.setMobileNo(M);
                  System.out.println(Purple+"\tYour Mobile No is Updated "+Reset);
                  User.Profile();
            }
            

      }
 }
 
class Owner {
      public static final String Reset="\u001B[0m";
     public static final String Red="\u001B[31m";
     public static final String Yellow="\u001B[33m";
     public static final String Green="\u001B[32m";
     public static final String Purple="\u001B[35m";
     public static final String Cyan="\u001B[36m";
     public static final String Blue="\u001B[34m";
      public static void addProperty() {
          Scanner sc = new Scanner(System.in);
          System.out.print(Blue+"\tEnter Property Name: "+Reset);
          String propertyName = sc.next();
          System.out.print(Green+"\tEnter Location: "+Reset);
          String location = sc.next();
          
          System.out.println(Red+"\tSelect Property Type: "+Reset);
          System.out.println(Yellow+"\t1. PG"+Reset);
          System.out.println(Purple+"\t2. Apartment"+Reset);
          System.out.println(Blue+"\t3. House"+Reset);
          System.out.println(Green+"\tChoose an option: "+Reset);
          int typeOption = sc.nextInt();
          String propertyType;
          String additionalInfo;
          switch (typeOption) {
              case 1:
                  propertyType = "PG";
                  System.out.println(Cyan+"\tSome Information About Property : "+Reset);
                  additionalInfo = sc.next();
                  break;
              case 2:
                  System.out.print(Yellow+"\tEnter Apartment Type (e.g., 1BHK, 2BHK, 3BHK): "+Reset);
                  propertyType = sc.next();
                  additionalInfo = sc.next();
                  break;
              case 3:
                  System.out.print(Red+"\tEnter House Size (e.g., 2-story, 3 rooms): ");
                  propertyType = sc.next();
                  additionalInfo = sc.next();
                  break;
              default:
                  System.out.println(Cyan+"\tInvalid option. Try again."+Reset);
                  return;
          }
          System.out.print(Red+"\tEnter Rent Amount : "+Reset);
          float amount =sc.nextFloat();
          Property property = new Property(propertyName, location, propertyType, additionalInfo,amount);
          AN.properties.add(property);
          System.out.println(Blue+"\tProperty added successfully!"+Reset);
          System.out.println(Yellow+"\tGo To Your Page"+Reset);
          Owner1.AfterOwnerlogin();
      }
  
      public static void searchPropertiesByLocation(String searchLocation) {
          boolean found = false;
          for (Property pro : AN.properties) {
              if (pro.getLocation().equalsIgnoreCase(searchLocation)) {
                  System.out.println(Green+""+Reset+pro);
                  System.out.println();
                  found = true;
              }
          }
          if (!found) {
              System.out.println(Green+"\tNo properties found for the location: " + searchLocation+Reset);
              Renter.searchLocation();
          }
      }
  
      public static void searchPropertiesByProperty(String searchProperty) {
          boolean found = false;
          for (Property pro : AN.properties) {
              if (pro.getType().equalsIgnoreCase(searchProperty)) {
                  System.out.println(pro);
                  System.out.println();
                  found = true;
              }
          }
          if (!found) {
              System.out.println(Purple+"\tNo properties found for the Property type: " + searchProperty+Reset);
              Renter.searchLocation();
          }
  
      }
  
      public static void searchPropertiesByLocationandProperty(String searchLocation,String searchProperty) {
          boolean found = false;
          for (Property pro : AN.properties) {
              if (pro.getType().equalsIgnoreCase(searchProperty)&& pro.getLocation().equalsIgnoreCase(searchLocation)) {
                  System.out.println(pro);
                  System.out.println();
                  found = true;
              }
          }
          if (!found) {
              System.out.println(Green+"\tNo properties found for the Property type: " + searchProperty+"and"+ searchLocation+Reset);
              Renter.searchLocation();
          }
          System.out.println("-----------------------------------------");
      }
      // Method to display all properties added by the owner
      public static void displayProperties() {
          if (AN.properties.isEmpty()) 
          {
              System.out.println(Blue+"No properties available."+Reset);
          } 
          else 
          {
              System.out.println(Yellow+"Properties:"+Reset);
              for (Property property : AN.properties) {
                  System.out.println(property);
                  System.out.println();
              }
          }
          System.out.println(Red+"Go To Your Page"+Reset);
          Owner1.AfterOwnerlogin();
      }
  }

class Property {
   private String propertyName;
   private String location;
   private String type;
   private String additionalInfo;
   private float amount;

   public Property(String propertyName, String location, String type, String additionalInfo,float amount) {
       this.propertyName = propertyName;
       this.location = location;
       this.type = type;
       this.additionalInfo = additionalInfo;
       this.amount=amount;
   }
   
   public String getPropertyName() {
       return propertyName;
   }

   public String getLocation() {
       return location;
   }

   public String getType() {
       return type;
   }

   public String getAdditionalInfo() {
       return additionalInfo;
   }
   public float getAmount() {
       return amount;
   }
   @Override
   public String toString() {
       return "Property Name: " + propertyName + ", Location: " + location +
              ", Type: " + type + ", Details: " + additionalInfo +", Amount: "+ amount;
   }
}

class Ownerlist{
   private String username;
   private String emailid;
   private String password;
   private String mobileno;
   Ownerlist()
   {

   }
   Ownerlist(String username,String emailid,String password,String mobileno){
        
        this.username=username;
        this.emailid=emailid;
        this.password=password;
        this.mobileno=mobileno;
        
   }
   void setUsername(String username)
   {
           this.username=username;
   }
   void setEmailId(String emailid){
         this.emailid=emailid;
   }
   
   void setPassword(String password){
         this.password=password;
   }
   void setMobileNo(String mobileno)
   {
           this.mobileno=mobileno;
   }
   String getUserName(){
         return username;
   }
   String getEmailId(){
         return emailid;
   }
   String getPassword(){
         return password;
   }
   String getMobileNo(){
         return mobileno;
   }
}

class Owner1{
    public static final String Reset="\u001B[0m";
	public static final String Red="\u001B[31m";
	public static final String Yellow="\u001B[33m";
	public static final String Green="\u001B[32m";
	public static final String Purple="\u001B[35m";
	public static final String Cyan="\u001B[36m";
    public static final String Blue="\u001B[34m";
    public static void Ownerregister(){
      
        System.out.println(Red+"\t--------------------------------------------------\n"+Reset);
        System.out.println(Blue+"\t\t          Register page"+Reset);
        System.out.println();
        System.out.println(Red+"\t--------------------------------------------------\n"+Reset);
        System.out.print(Yellow+"\tEnter your user name: "+Reset);
        String username=AN.sc.next();
        
        System.out.print(Blue+"\tEnter your email id: "+Reset);
        String emailid=AN.sc.next();
        
        while(!AN.isValidEmail(emailid)){
           System.out.println(Green+"\tIt must contain atleast one uppercase,lowercase letters,numbers"+Reset);
           emailid=AN.sc.next();
        }
        
        System.out.print(Cyan+"\tEnter Password: "+Reset);
        String password=AN.sc.next();
     
        while(!AN.isValidPassword(password)){
           System.out.println(Purple+"\tIt must contain no space,atleast 8 characters,one uppercase letter ,lowercase letter,numbers and special character"+Reset);
           password=AN.sc.next();
        }
        System.out.print(Yellow+"\tEnter Confirm Password: "+Reset);
        String confirmpassword=AN.sc.next();
        while(!password.equals(confirmpassword)){
           System.out.println(Green+"\tPassword doesn't match"+Reset);
           confirmpassword=AN.sc.next();
        }
        
        System.out.print(Blue+"\tEnter your mobile number: "+Reset);
        String mobileno=AN.sc.next();
        while(!AN.isValidMobileNumber(mobileno)){
           System.out.println(Purple+"\tEnter valid mobile number"+Reset);
           mobileno=AN.sc.next();
        }
        
        
        AN.Odetails.add(new Ownerlist(username,emailid,password,mobileno));
        
        AN.otpgenerate();
     }
     
     public static void Ownerlogin(){

        System.out.println();
        System.out.println(" \t\t           Login page");
        System.out.println("\t-----------------------------------------\n");
        System.out.print(Cyan+"\tEnter your user name or emailid: "+Reset);
        String s=AN.sc.next();
        System.out.print(Yellow+"\tEnter your password: "+Reset);
        String password=AN.sc.next();
        
        if(AN.Odetails.isEmpty())
        {
          System.out.println(Green+"\tuser doesn't have an account and need to register "+Reset);
          System.out.println(Blue+"\t\tGo To Home"+Reset);
            AN.home();
        }
        for(Ownerlist us:AN.Odetails){
           
             if((us.getUserName().equals(s)||us.getEmailId().equals(s)) && us.getPassword().equals(password)){
                  System.out.println(Purple+"\t\tLogin successfully"+Reset);
                    AfterOwnerlogin();
                  break;
             }
             else
             {
                System.out.println(Red+"\tInvalid username or password ."+Reset);
                System.out.println(Blue+"\t1.Try Again \n\t2.Go to Home"+Reset);
                int a = AN.sc.nextInt();
                if(a==1)
                {
                    Ownerlogin();
                }
                else
                {
                    AN.home();
                }
            }
        }
     }
        public static void AfterOwnerlogin()
        {
            System.out.println(Yellow+"\t1. Add a Property"+Reset);
            System.out.println(Green+"\t2. View All Properties"+Reset);
            System.out.println(Blue+"\t3. Profile"+Reset);
            System.out.println(Red+"\t4. Logout"+Reset);
            System.out.print(Purple+"\tChoose an option: "+Reset);
            int ownerOption = AN.sc.nextInt();
            if (ownerOption == 1) 
            {
                Owner.addProperty();
            } 
            else if (ownerOption == 2) 
            {
                Owner.displayProperties();
            } 
            else if(ownerOption==3)
            {
                OwnerProfile();
            }
            else 
            {
                AN.home();
            }
   
        }
        public  static void OwnerProfile()
        {     
            System.out.println(Blue+"\tUser Name : "+ AN.q.getUserName()+Reset);
            System.out.println(Purple+"\tEmail Id : "+ AN.q.getEmailId()+Reset);
            System.out.println(Yellow+"\tMobil no : " + AN.q.getMobileNo()+Reset);
            System.out.println(Cyan+"\t1. Go To Your Page \n\t2. Modify Your Details \n\t3. Logout"+Reset);
            int y=AN.sc.nextInt();
            if(y==1)
            {
                AfterOwnerlogin();
            }
            else if(y==2)
            {
                OwnerModify();
            }
            else
            {
                AN.home();
            }
      }
      static Ownerlist f=new Ownerlist();
      public static void OwnerModify()
      {
            System.out.println(Cyan+"\tSelect an option to modify your details "+Reset);
            System.out.println(Purple+"\t1. User name \n\t2. EmailId \n\t3. Password \n\t4. Mobile no"+Reset);
            int z=AN.sc.nextInt();
            if(z==1)
            {
                  String U=AN.sc.next();
                  f.setUsername(U);
                  System.out.println(Green+"\tYour User Name is Updated "+Reset);
                  OwnerProfile();
            }
            else if(z==2)
            {
                  String E=AN.sc.next();
                  f.setEmailId(E);
                  System.out.println(Green+"\tYour Email Id is Updated "+Reset);
                  OwnerProfile();
            }
            else if(z==3)
            {
                  String P=AN.sc.next();
                  f.setPassword(P);
                  System.out.println(Green+"\tYour password is Updated "+Reset);
                  OwnerProfile();
            }
            else
            {
                  String M=AN.sc.next();
                  f.setMobileNo(M);
                  System.out.println(Green+"\tYour Mobile No is Updated "+Reset);
                  OwnerProfile();
            }
      }
 }

