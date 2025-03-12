package com.lshzzz.mato.model.Map;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "map")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Map {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id", nullable = false)
	private Long userId; // 유저 ID (방장 또는 생성자)

	@Column(name = "name", nullable = false, length = 50)
	private String name; // 맵 이름

	@Column(name = "explanation", columnDefinition = "TEXT")
	private String description; // 맵 설명

	@Column(name = "map_status", nullable = false)
	private Boolean isPublic; // 맵 상태 (예: 0 = 비공개, 1 = 공개)

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt; // 생성 시간

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
	}
}

