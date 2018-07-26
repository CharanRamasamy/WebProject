package main.javafiles;

public class Customers {
	
	private String firstname;
    private String lastname;
    private String emailid;
    private String phnumber;
    private String addr;
    
    public void setFirstName(String firstname) 
    { 
    
        this.firstname = firstname;
        
    }
    public String getFirstName() 
    { 
        return (this.firstname);
    }
    
    public void setLastName(String lastname) 
    { 
    
        this.lastname = lastname;
        
    }
    public String getLastName() 
    { 
        return (this.lastname);
    }
    
    public void setEmail(String emailid) {
    	
    	this.emailid = emailid;
    }
    
    public String getEmail() 
    { 
        return (this.emailid);
    }
    
    
    public void setPhone(String phnumber) {
    	
    	this.phnumber = phnumber;
    }
    
    public String getPhone() 
    { 
        return (this.phnumber);
    }
    
   public void setAddress(String addr) {
    	
    	this.addr = addr;
    }
    
    public String getAddress() 
    { 
        return (this.addr);
    }
    

}
