package mk.ukim.finki.mendo.service;

public interface AuthorizationService {
    // ActivityTag
    void canViewActivityTags();
    void canManageActivityTags();

    // Application
    void canViewApplications();
    void canManageApplications();

    // Article
    void canViewArticles();
    void canManageArticles();


    // Category
    void canViewCategories();
    void canManageCategories();

    // Certificate
    void canViewCertificates();
    void canManageCertificates();

    // Competition
    void canViewCompetitions();
    void canManageCompetitions();

    // CompetitionCycle
    void canViewCompetitionCycles();
    void canManageCompetitionCycles();

    // CompetitionTask
    void canViewCompetitionTasks();
    void canManageCompetitionTasks();

    // Content
    void canViewContents();
    void canManageContents();

    // Lecture
    void canViewLectures();
    void canManageLectures();

    // MendoUser
    void canViewMendoUsers();
    void canManageMendoUsers();

    // Participation
    void canViewParticipations();
    void canManageParticipations();

    // Permission
    void canViewPermissions();
    void canManagePermissions();

    // Post
    void canViewPosts();
    void canManagePosts();

    // Question
    void canViewQuestions();
    void canManageQuestions();

    // Quota
    void canViewQuotas();
    void canManageQuotas();

    // Role
    void canViewRoles();
    void canManageRoles();

    // Rooms
    void canViewRooms();
    void canManageRooms();

    // School
    void canViewSchools();
    void canManageSchools();

    // Submission
    void canViewSubmissions();
    void canManageSubmissions();

    // Task
    void canViewTasks();
    void canManageTasks();

    // TestCase
    void canViewTestCases();
    void canManageTestCases();

    // TestGroup
    void canViewTestGroups();
    void canManageTestGroups();

    // Thread
    void canViewThreads();
    void canManageThreads();

    // Topic
    void canViewTopics();
    void canManageTopics();

    void canViewSchedule();

    void canManageSchedule();

    void canViewResults();

    void canManageResults();
}