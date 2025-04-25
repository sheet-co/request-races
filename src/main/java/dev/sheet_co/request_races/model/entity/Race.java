package dev.sheet_co.request_races.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(includeFieldNames = true, onlyExplicitlyIncluded = true)
@Table(name = "races")
public class Race {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @ToString.Include
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(name = "name")
  @ToString.Include
  @NotNull
  private String name;

  @Column(name = "color")
  @ToString.Include
  @NotNull
  private String color;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime updatedAt;
}
