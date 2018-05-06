package com.nemo.askquare.service.impl;

import com.nemo.askquare.annotation.Transactional;
import com.nemo.askquare.exception.ApplicationException;
import com.nemo.askquare.exception.ValidationException;
import com.nemo.askquare.form.LoginForm;
import com.nemo.askquare.form.SignupForm;
import com.nemo.askquare.model.*;
import com.nemo.askquare.repository.*;
import com.nemo.askquare.service.CommonService;
import org.apache.commons.mail.EmailException;

import java.util.List;
import java.util.UUID;

class CommonServiceImpl implements CommonService {

    private final AccountRepository accountRepository;
    private final AccountRoleRepository accountRoleRepository;
    private final AccountRegistrationRepository accountRegistrationRepository;
    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    CommonServiceImpl(AccountRepository accountRepository, AccountRoleRepository accountRoleRepository,
                      AccountRegistrationRepository accountRegistrationRepository, TestRepository testRepository,
                      QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.accountRepository = accountRepository;
        this.accountRoleRepository = accountRoleRepository;
        this.accountRegistrationRepository = accountRegistrationRepository;
        this.testRepository = testRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Transactional
    public Account login(LoginForm form) throws ValidationException {
        Account account = accountRepository.findByLogin(form.getLogin());
        if (account == null) {
            throw new ValidationException("Account not found by login");
        }
        if (!account.getPassword().equals(form.getPassword())) {
            throw new ValidationException("Invalid password");
        }
        if (!account.getActive()) {
            throw new ValidationException("Account is not active");
        }
        int permittedRole = accountRoleRepository.findAccountRole(account.getId()).getRoleId();
        if (form.getRole() < permittedRole) { // 1-admin...4-student
            throw new ValidationException("You don't have access to this role");
        }
        account.setRoleId(form.getRole());
        return account;
    }

    @Override
    @Transactional
    public Account findById(long id) {
        return accountRepository.findById(id);
    }

    public String generateRememberMeToken(Account account) {
        return UUID.randomUUID().toString();
    } //TODO real

    public Account findByRememberMeToken(String rememberMeToken) {
        if (rememberMeToken == null || rememberMeToken.trim().length() == 0) {
            return null;
        }
        return new Account(1l, "admin", "admin"); //TODO retrieve a real account
    }

    @Override
    @Transactional
    public AccountRole findAccountRole(long accountId) {
        return accountRoleRepository.findAccountRole(accountId);
    }

    @Override
    @Transactional(readOnly = false)
    public Account signup(SignupForm form) throws ValidationException {
        Account account = accountRepository.findByLogin(form.getLogin());
        if (account != null) {
            throw new ValidationException("This username is already taken!");
        }
        account = accountRepository.findByEmail(form.getEmail());
        if (account != null) {
            throw new ValidationException("This email is already registered!");
        }
        account = new Account(form.getLogin(), form.getPassword(), form.getFirstName(), form.getLastName(),
                form.getSecondName(), form.getEmail());
        account = accountRepository.save(account);
        AccountRole accountRole = new AccountRole(account.getId(), 4);
        save(accountRole);
        return account;
    }

    @Override
    @Transactional(readOnly = false)
    public void createConfirmation(Account account, String requestURL) {
        String code = UUID.randomUUID().toString();
        AccountRegistration confirmationEntity = new AccountRegistration(account.getId(), code);
        confirmationEntity = accountRegistrationRepository.saveConfirmation(confirmationEntity);
        int slashIndex = requestURL.lastIndexOf("/");// obtains the current host name
        String confirmationURL = requestURL.substring(0, slashIndex).toString() +
                "/confirm?code=" + confirmationEntity.getCode();

        String message = "Please click on the link to complete your registration: " + confirmationURL;
        String subject = "Verify your account";
        Thread confirmationThread = new Thread(() -> {
            try {
                EmailService.sendConfirmation(account.getEmail(), subject, message);
            } catch (EmailException e) {
                throw new ApplicationException(e);
            }
        });
        confirmationThread.start();
    }

    @Override
    @Transactional(readOnly = false)
    public AccountRole save(AccountRole accountRole) {
        return accountRoleRepository.save(accountRole);
    }

    @Override
    @Transactional
    public Long count() {
        return accountRepository.count();
    }

    @Override
    @Transactional
    public AccountRegistration findRegistrationById(long id) {
        return accountRegistrationRepository.findById(id);
    }

    @Override
    @Transactional
    public AccountRegistration findByCode(String code) {
        return accountRegistrationRepository.findByCode(code);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateActive(Boolean active, long id) {
        accountRepository.updateActive(active, id);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateAccount(String login, String password, String firstName, String secondName, String lastName, long id) {
        accountRepository.updateAccount(login, password, firstName, secondName, lastName, id);
    }

    @Override
    @Transactional
    public List<Test> findAllTests() {
        return testRepository.findAll();
    }

    @Override
    @Transactional
    public List<Question> findAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    @Transactional
    public List<Question> findQuestionsByTestId(long testId) {
        return questionRepository.findByTestId(testId);
    }

    @Override
    @Transactional
    public List<Answer> findAllAnswers() {
        return answerRepository.findAll();
    }

    @Override
    @Transactional
    public List<Answer> findAnswersByQuestionId(long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    @Override
    @Transactional
    public List<Answer> findAnswersByTestId(long testId) {
        return answerRepository.findByTestId(testId);
    }
}
