package data.service;

import data.repository.AssignmentRepo;
import org.springframework.stereotype.Service;
import data.Stu_Assignments;

import java.util.List;

@Service
public class CrmService {

    private final AssignmentRepo assignmentRepo;

    public CrmService(AssignmentRepo assignmentRepo){

        this.assignmentRepo = assignmentRepo;
    }
    public List<Stu_Assignments> findAllAssignment(){
        return assignmentRepo.findAll();
    }

}
