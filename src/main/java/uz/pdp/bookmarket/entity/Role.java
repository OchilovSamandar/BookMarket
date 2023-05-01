package uz.pdp.bookmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.bookmarket.entity.enums.PermissionName;
import uz.pdp.bookmarket.entity.template.AbstractEntity;

import javax.persistence.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AbstractEntity {


    @Column(nullable = false, unique = true)
    private String name;//admin user

    @Column(nullable = false, columnDefinition = "text",length = 100)
    private String description;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<PermissionName> permissionList;
}
