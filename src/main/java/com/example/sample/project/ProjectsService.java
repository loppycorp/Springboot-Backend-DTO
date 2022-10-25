package com.example.sample.project;


import org.springframework.stereotype.Service;
import java.util.Collection;


@Service
public interface ProjectsService {
    Projects create(Projects projects);
    Collection<Projects> list(int limit);
    Projects get(Long id);
    Projects archive(Long id);
    Projects update(Long id,
                    String projectName);

}
