package com.nemo.askquare.service;

import com.nemo.askquare.exception.ValidationException;
import com.nemo.askquare.form.LoginForm;
import com.nemo.askquare.form.SignupForm;
import com.nemo.askquare.model.*;

import java.util.List;

public interface CommonService {

    Account login(LoginForm form) throws ValidationException;

    Account findById(long id);

    String generateRememberMeToken(Account account); //TODO add a new table in DB to store tokens

    Account findByRememberMeToken(String rememberMeToken);

    AccountRole findAccountRole(long accountId);

    Account signup(SignupForm form) throws ValidationException;

    void createConfirmation(Account account, String requestURL);

    AccountRole save(AccountRole accountRole);

    Long count();

    AccountRegistration findRegistrationById(long id);

    AccountRegistration findByCode(String code);

    void updateActive(Boolean active, long id);

    void updateAccount(String login, String password, String firstName, String secondName, String lastName, long id);

    List<Test> findAllTests();

    List<Question> findAllQuestions();

    List<Question> findQuestionsByTestId(long testId);

    List<Answer> findAllAnswers();

    List<Answer> findAnswersByQuestionId(long questionId);

    List<Answer> findAnswersByTestId(long testId);

}
