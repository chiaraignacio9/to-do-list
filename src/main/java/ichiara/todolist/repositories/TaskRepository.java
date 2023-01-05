package ichiara.todolist.repositories;

import ichiara.todolist.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {

    @Query(value = "SELECT t_task.id FROM t_task INNER JOIN _users ON t_task.id_user = _users.user_id AND _users.user_id =:id", nativeQuery = true)
    public List<String> findTaskIdByUserId(Long id);

}
