package com.aster.app.Exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {
	private LocalDateTime timestamp;
	private String message;
	private String details;
}
// This class is for making custom error messages