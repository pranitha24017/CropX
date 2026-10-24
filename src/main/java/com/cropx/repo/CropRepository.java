package com.cropx.repo;
import com.cropx.model.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CropRepository extends JpaRepository<Crop, Long> {}
