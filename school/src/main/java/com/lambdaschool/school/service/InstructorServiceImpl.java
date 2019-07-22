package com.lambdaschool.school.service;

import com.lambdaschool.school.exceptions.ResourceNotFoundException;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "instructorService")
public class InstructorServiceImpl implements InstructorService
{
    @Autowired
    private InstructorRepository instructrepos;

    @Override
    public Instructor save(Instructor instructor) {
        return instructrepos.save(instructor);
    }

    @Override
    public Instructor update(Instructor instructor, long id) {
        Instructor newInstructor = instructrepos.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));

        if (instructor.getInstructname() != null) {
            newInstructor.setInstructname(instructor.getInstructname());
        }

        if (instructor.getCourses() != null) {
            newInstructor.setCourses(instructor.getCourses());
        }

        return instructrepos.save(newInstructor);
    }

    @Transactional
    @Override
    public void delete(long id) {
        if (instructrepos.findById(id).isPresent()) {
            instructrepos.deleteById(id);
        } else {
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }
}
