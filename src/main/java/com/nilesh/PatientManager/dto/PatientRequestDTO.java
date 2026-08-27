package com.nilesh.PatientManager.dto;

import com.nilesh.PatientManager.dto.validation.CreatePatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PatientRequestDTO {

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100,message = "Name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Enter a valid email")
    private String email;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @NotBlank(message = "Required Date of Birth")
    private String dob;

    @NotBlank(groups = CreatePatientValidationGroup.class, message =
            "Registered date is required")
    private String regestrationDate;

}
