package com.atomic.pafyplatform.request;

import com.atomic.pafyplatform.models.Gender;
import io.micrometer.common.lang.Nullable;
import lombok.Data;

@Data
public class UpdateUser {
	@Nullable
    private String firstName;
	@Nullable
    private String lastName;
	@Nullable
    private String username;
	@Nullable
    private Gender gender;
}
