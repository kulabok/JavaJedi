package ua.com.javajedi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.javajedi.db.AnswerRepository;
import ua.com.javajedi.model.Answer;
import ua.com.javajedi.model.Exercise;
import ua.com.javajedi.model.User;

import java.util.List;
import java.util.Objects;

@Service
public class AnswerServiceImpl implements AnswerService {
    private AnswerRepository answerRepository;
    private ExerciseService exerciseService;
    private UserServiceDB userService;

    @Override
    public Answer add(Answer answer) {
        return answerRepository.saveAndFlush(answer);
    }

    @Override
    public Answer update(Answer answer) {
        return answerRepository.saveAndFlush(answer);
    }

    @Override
    public Answer delete(Answer answer) {
        answerRepository.delete(answer.getAnswerId());
        return answer;
    }

    @Override
    public Answer findById(Answer answer) {
        return answerRepository.findOne(answer.getAnswerId());
    }

    @Override
    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    @Override
    public List<Answer> findAllByExerciseId(long exerciseId) {
        return answerRepository.findAllByExerciseId(exerciseId);
    }

    @Override
    public String resolveAnswer(String exerciseId, String answer, User user) {
        long id = Long.parseLong(exerciseId);
        List<Answer> answers = answerRepository.findAllByExerciseId(id);
        for (Answer a : answers ) {
            if (a.isCorrect() && Objects.equals(a.getContent(), answer)){
                List<Exercise> done = exerciseService.findAllDone(user.getUserId());
                done.add(exerciseService.findById(id));
                user.setAlreadyDone(done);
                userService.update(user);
                return "Correct!";
            }
        }
        return "No! Study harder!";
    }

    @Autowired
    public void setAnswerRepository(AnswerRepository answerRepository){
        this.answerRepository = answerRepository;
    }

    @Autowired
    public void setExerciseService(ExerciseService exerciseService){
        this.exerciseService = exerciseService;
    }

    @Autowired
    public void setUserService(UserServiceDB userService){
        this.userService = userService;
    }
}
