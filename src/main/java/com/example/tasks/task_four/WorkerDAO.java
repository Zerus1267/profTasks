package com.example.tasks.task_four;

import com.example.tasks.task_one.Worker;

import java.util.List;

public interface WorkerDAO {
    public void saveWorkers(List<Worker> workers);
    public List<Worker> getAllWorkers();
}
