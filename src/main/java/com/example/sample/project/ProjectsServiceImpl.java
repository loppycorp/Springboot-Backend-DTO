package com.example.sample.project;

import com.example.sample.department.Department;
import com.example.sample.department.DepartmentRepository;
import com.example.sample.department.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ProjectsServiceImpl implements ProjectsService {

    private final ProjectsRepository projectsRepository;

    @Override
    public Projects create(Projects projects) {
        log.info("added");
        return projectsRepository.save(projects);

    }

    @Override
    public Collection<Projects> list(int limit) {
        log.info("List of all employees");
        return projectsRepository.findAll(PageRequest.of(0, limit)).toList();
    }


    @Override
    public Projects get(Long id) {
        log.info("EmployeeId: " + id);
        return projectsRepository.findById(id).get();
    }


    @Override
    public Projects update(Long id,
                           String projectName) {
        log.info("EmployeeId: " + id + "updated");
        Projects getId = projectsRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        id + "doesn't exist"));
        if (projectName != null && projectName.length() > 0 && !Objects.equals(getId.getProjectName(), projectName)) {
            getId.setProjectName(projectName);
        }

        return getId;
    }

    @Override
    public Projects archive(Long id) {
        log.info("Employee Archived");
        projectsRepository.deleteById(id);
        return null;
    }
}
