package com.psb.Entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "jpausers", indexes = {@Index(name = "UK_USERS_EMAIL", columnList = "email", unique = true)})
@DynamicUpdate
@DynamicInsert
public class User implements Serializable {
	
	private static final long serialVersionUID = -563329217866858622L;
	
	@Builder
	public User(String type, String name, String email, String sex, String birthDate,
			String phoneNumber, String password) {
		this.type = type;
		this.name = name;
		this.email = email;
		this.sex = sex;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false, columnDefinition = "INT UNSIGNED")
	private Long id;

	@ColumnDefault(value = "1")
	@Column(nullable = false, length = 10, columnDefinition = "CHAR(1)")
	private String type;


	@Column(nullable = false, length = 100)
	private String email;

	@Column(nullable = false, length = 50)
	private String name;

	@ColumnDefault(value = "1")
	@Column(nullable = false, length = 10, columnDefinition = "CHAR(1)")
	private String sex;

	@Column(nullable = false, length = 20)
	private String birthDate;

	@Column(nullable = false, length = 20)
	private String phoneNumber;

	@JsonIgnore
	@Column(nullable = false, length = 150)
	private String password;

	@Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
	private boolean active;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true, columnDefinition = "TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP")
	private Date updatedAt;

	@PrePersist
	protected void onCreate() {
		createdAt = Timestamp.valueOf(LocalDateTime.now());
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = Timestamp.valueOf(LocalDateTime.now());
	}




	
}
