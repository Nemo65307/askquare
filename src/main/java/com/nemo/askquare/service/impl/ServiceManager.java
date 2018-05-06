package com.nemo.askquare.service.impl;

import com.nemo.askquare.repository.*;
import com.nemo.askquare.service.AdminService;
import com.nemo.askquare.service.CommonService;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import java.sql.SQLException;

public final class ServiceManager {

    private static final String SERVICE_MANAGER = "SERVICE_MANAGER";

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final AccountRoleRepository accountRoleRepository;
    private final AccountRegistrationRepository accountRegistrationRepository;
    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final CommonService commonService;
    private final BasicDataSource dataSource;
    private final AdminService adminService;

    private ServiceManager() {
        dataSource = buildDataSource();
        accountRepository = RepositoryFactory.createRepository(AccountRepository.class);
        accountRoleRepository = RepositoryFactory.createRepository(AccountRoleRepository.class);
        roleRepository = RepositoryFactory.createRepository(RoleRepository.class);
        testRepository = RepositoryFactory.createRepository(TestRepository.class);
        questionRepository = RepositoryFactory.createRepository(QuestionRepository.class);
        answerRepository = RepositoryFactory.createRepository(AnswerRepository.class);
        accountRegistrationRepository = RepositoryFactory.createRepository(AccountRegistrationRepository.class);
        commonService = (CommonService) ServiceFactory.createService(dataSource, new CommonServiceImpl(
                accountRepository, accountRoleRepository, accountRegistrationRepository,
                testRepository, questionRepository, answerRepository));
        adminService = (AdminService) ServiceFactory.createService(dataSource, new AdminServiceImpl(accountRepository, roleRepository));
    }

    public CommonService getCommonService() {
        return commonService;
    }

    public AdminService getAdminService() {
        return adminService;
    }

    public static ServiceManager getInstance(ServletContext context) {
        ServiceManager instance = (ServiceManager) context.getAttribute(SERVICE_MANAGER);
        if (instance == null) {
            instance = new ServiceManager();
            context.setAttribute(SERVICE_MANAGER, instance);
        }
        return instance;
    }

    public void shutdown() {
        try {
            dataSource.close();
        } catch (SQLException e) {
            e.printStackTrace(); // TODO replace with logger
        }
    }

    private BasicDataSource buildDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost/askquare");
        dataSource.setUsername("admin");
        dataSource.setPassword("password");
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(20);
        return dataSource;
    }

}
