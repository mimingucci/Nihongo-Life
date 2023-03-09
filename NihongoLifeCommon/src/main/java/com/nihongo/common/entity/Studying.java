package com.nihongo.common.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
public abstract class Studying {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    private String logo;

    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

	public Studying() {
		super();
	}

}
