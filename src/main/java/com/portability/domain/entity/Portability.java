package com.portability.domain.entity;

import com.portability.domain.entity.enums.PortabilityStatus;
import com.portability.domain.entity.enums.TelephoneCompany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
@Table(name = "tb_portability")
public class Portability implements Serializable {


    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID portabilityId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TelephoneCompany source;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TelephoneCompany target;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status")
    private PortabilityStatus portabilityStatus;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;
}

