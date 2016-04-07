package org.andy.shop.action;

import java.util.Collection;
import java.util.List;

import org.andy.shop.entity.Person;
import org.andy.shop.entity.UserInfo;

public class HelloWordAction {
    
    private Integer productId;
    private String productName;
    private String productDesc;
    private List<UserInfo> infos;
    

	public List<UserInfo> getInfos() {
		return infos;
	}

	public void setInfos(List<UserInfo> infos) {
		this.infos = infos;
	}
	
	private Collection<Person> persons;
	
    public String savePs(){
    	for (Person person : persons) {
    		System.out.println(person.toString());
		}
        return "hello_world";
    }
	
	public Collection<Person> getPersons() {
		return persons;
	}

	public void setPersons(Collection<Person> persons) {
		this.persons = persons;
	}

	private double productPrice;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public HelloWordAction(Integer productId, String productName, String productDesc,
            double productPrice) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
    }
    
    public HelloWordAction() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName="
                + productName + ", productDesc=" + productDesc
                + ", productPrice=" + productPrice + "]";
    }
    
    public String execute(){
        System.out.println(productName);
        System.out.println(productDesc);
        System.out.println(productPrice);
        return "hello_world";
    }
    

    
    public String saveUs(){
    	for (int i = 0; i < infos.size(); i++) {
			System.out.println(infos.get(i).toString());
		}
        return "hello_world";
    }

}
