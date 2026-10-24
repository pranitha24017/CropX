package com.cropx.repo;
import com.cropx.model.BuyerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderRepository extends JpaRepository<BuyerOrder, Long> {}
