//package com.farmbridge.entities;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//
//
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Pattern;
//import jakarta.validation.constraints.Size;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//@Entity
//@Table(name="buyer")
//@Getter
//@Setter
//@ToString(callSuper=true,exclude ="password")
//public class Buyer extends BaseEntity {
//   
//
//    @NotNull
//    @Size(max = 255)
//    private String fullName;
//
//    @NotNull
//    @Size(max = 255)
//    private String address;
//
//    @NotNull
//  
//    @Column(unique = true)
//    @Pattern(
//	        regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
//	        message = "Invalid email address"
//	    )
//    private String email;
//
//    @NotNull
//    @Size(min = 6, max = 6)
//    private String pincode;
//
//    @NotNull
//    @Size(min = 10, max = 10)
//     @Column(name="contact_no",unique=true)
//	@Pattern(regexp = "\\+?[0-9]{7,15}", message = "Invalid contact number")
//    private String contactNo;
//
//    @NotNull
//    @Size(min = 6,message = "The value must have at least 6 characters")
//    private String password;
//    
//	@OneToMany(mappedBy="buyer", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Order> orders=new ArrayList<Order>();
//
//	@Column(name="role")
//	private String role="Buyer";
//}

package com.farmbridge.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="buyer")
@Getter
@Setter
@ToString(callSuper=true, exclude={"password","orders","cart"})
public class Buyer extends BaseEntity {
   
    @NotNull
    @Size(max = 255)
    private String fullName;

    @NotNull
    @Size(max = 255)
    private String address;

    @NotNull
    @Column(unique = true)
    @Pattern(
        regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
        message = "Invalid email address"
    )
    private String email;

    @NotNull
    @Size(min = 6, max = 6)
    private String pincode;

    @NotNull
    @Size(min = 10, max = 10)
    @Column(name="contact_no", unique=true)
    @Pattern(regexp = "\\+?[0-9]{7,15}", message = "Invalid contact number")
    private String contactNo;

    @NotNull
    @Size(min = 6, message = "The value must have at least 6 characters")
    private String password;
    
    @OneToMany(mappedBy="buyer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Order> orders = new ArrayList<>();

    @Column(name="role")
    private String role = "ROLE_Buyer";
    
    @OneToOne
    (mappedBy="buyer",cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;
    
    
}


