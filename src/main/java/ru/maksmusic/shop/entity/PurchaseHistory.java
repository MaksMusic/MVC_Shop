package ru.maksmusic.shop.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "purchase_history")
public class PurchaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "purchase_date", nullable = false)
    private Timestamp purchaseDate;

    // Getters and setters
}