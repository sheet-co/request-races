package dev.sheet_co.request_races.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(includeFieldNames = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "races_request")
public class RaceRequest {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @ToString.Include
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column
  @EqualsAndHashCode.Include
  @ToString.Include
  @NotNull
  private String name;

  @Column
  @EqualsAndHashCode.Include
  @ToString.Include
  @NotNull
  private String color;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;
}
