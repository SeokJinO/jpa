package com.example.jpa.controller;

import com.example.jpa.entity.CrudEntity;
import com.example.jpa.repository.CrudEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class CrudController {

    private final CrudEntityRepository crudEntityRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("search")
    public String searchAllMember() {
        return crudEntityRepository.findAll().toString();
    }

    @GetMapping("searchParam")
    public String searchParamMember(@RequestParam(value = "age") int age) {
        List resultList = entityManager.createQuery("select name from sample_member where age > :age")
                .setParameter("age", age)
                .getResultList();
        return resultList.toString();
    }

    @GetMapping("searchParamRepo")
    public String searchParamRepoMember(@RequestParam(value = "name", required = false) String name) {
        return crudEntityRepository.searchParamRepo(name).toString();
    }

    @PostMapping("insert")
    public String insertMember(@RequestBody CrudEntity crudEntity) {
        if(crudEntityRepository.findById(crudEntity.getName()).isPresent()) {
            return "동일한 이름 있음";
        } else {
            CrudEntity entity = CrudEntity.builder().name(crudEntity.getName()).age(crudEntity.getAge()).build();
            crudEntityRepository.save(crudEntity);
            return "이름 : " + crudEntity + " 나이 : " + crudEntity + "으로 추가됨";
        }
    }

    @DeleteMapping("delete")
    public String deleteMember(@RequestParam(value = "name") String name) {
        if(crudEntityRepository.findById(name).isEmpty()) {
            return "입력한 " + name + "이 존재하지 않습니다";
        } else {
            crudEntityRepository.delete(CrudEntity.builder().name(name).build());
            return name + " 삭제 완료";
        }
    }

    @PutMapping("update")
    public String updateMember(@RequestParam(value = "name") String name, @RequestParam(value = "age") int age) {
        if(crudEntityRepository.findById(name).isEmpty()) {
            return "입력한 " + name + "이 존재하지 않습니다.";
        } else {
            crudEntityRepository.save(CrudEntity.builder().name(name).age(age).build());
            return name + "의 나이를" + age + "로 변경 완료";
        }
    }
}
