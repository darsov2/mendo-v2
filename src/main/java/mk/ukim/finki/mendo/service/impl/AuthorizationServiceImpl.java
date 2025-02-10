package mk.ukim.finki.mendo.service.impl;

import mk.ukim.finki.mendo.service.AuthorizationService;
import mk.ukim.finki.mendo.service.MendoUserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    private final MendoUserService userService;

    public AuthorizationServiceImpl(MendoUserService userService) {
        this.userService = userService;
    }

    @Override
    public void canViewActivityTags() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_ACTIVITY_TAG"))) {
            throw new AccessDeniedException("User is not authorized to view activity tags");
        }
    }

    @Override
    public void canManageActivityTags() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_ACTIVITY_TAG"))) {
            throw new AccessDeniedException("User is not authorized to manage activity tags");
        }
    }

    @Override
    public void canViewApplications() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_APPLICATION"))) {
            throw new AccessDeniedException("User is not authorized to view applications");
        }
    }

    @Override
    public void canManageApplications() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_APPLICATION"))) {
            throw new AccessDeniedException("User is not authorized to manage applications");
        }
    }

    @Override
    public void canViewArticles() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_ARTICLE"))) {
            throw new AccessDeniedException("User is not authorized to view articles");
        }
    }

    @Override
    public void canManageArticles() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_ARTICLE"))) {
            throw new AccessDeniedException("User is not authorized to manage articles");
        }
    }

    @Override
    public void canViewCategories() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_CATEGORY"))) {
            throw new AccessDeniedException("User is not authorized to view categories");
        }
    }

    @Override
    public void canManageCategories() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_CATEGORY"))) {
            throw new AccessDeniedException("User is not authorized to manage categories");
        }
    }

    @Override
    public void canViewCertificates() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_CERTIFICATE"))) {
            throw new AccessDeniedException("User is not authorized to view certificates");
        }
    }

    @Override
    public void canManageCertificates() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_CERTIFICATE"))) {
            throw new AccessDeniedException("User is not authorized to manage certificates");
        }
    }

    @Override
    public void canViewCompetitions() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_COMPETITION"))) {
            throw new AccessDeniedException("User is not authorized to view competitions");
        }
    }

    @Override
    public void canManageCompetitions() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_COMPETITION"))) {
            throw new AccessDeniedException("User is not authorized to manage competitions");
        }
    }

    @Override
    public void canViewCompetitionCycles() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_COMPETITION_CYCLE"))) {
            throw new AccessDeniedException("User is not authorized to view competition cycles");
        }
    }

    @Override
    public void canManageCompetitionCycles() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_COMPETITION_CYCLE"))) {
            throw new AccessDeniedException("User is not authorized to manage competition cycles");
        }
    }

    @Override
    public void canViewCompetitionTasks() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_COMPETITION_TASK"))) {
            throw new AccessDeniedException("User is not authorized to view competition tasks");
        }
    }

    @Override
    public void canManageCompetitionTasks() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_COMPETITION_TASK"))) {
            throw new AccessDeniedException("User is not authorized to manage competition tasks");
        }
    }

    @Override
    public void canViewContents() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_CONTENT"))) {
            throw new AccessDeniedException("User is not authorized to view contents");
        }
    }

    @Override
    public void canManageContents() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_CONTENT"))) {
            throw new AccessDeniedException("User is not authorized to manage contents");
        }
    }

    @Override
    public void canViewLectures() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_LECTURE"))) {
            throw new AccessDeniedException("User is not authorized to view lectures");
        }
    }

    @Override
    public void canManageLectures() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_LECTURE"))) {
            throw new AccessDeniedException("User is not authorized to manage lectures");
        }
    }

    @Override
    public void canViewMendoUsers() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_MENDO_USER"))) {
            throw new AccessDeniedException("User is not authorized to view mendo users");
        }
    }

    @Override
    public void canManageMendoUsers() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_MENDO_USER"))) {
            throw new AccessDeniedException("User is not authorized to manage mendo users");
        }
    }

    @Override
    public void canViewParticipations() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_PARTICIPATION"))) {
            throw new AccessDeniedException("User is not authorized to view participations");
        }
    }

    @Override
    public void canManageParticipations() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_PARTICIPATION"))) {
            throw new AccessDeniedException("User is not authorized to manage participations");
        }
    }

    @Override
    public void canViewPermissions() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_PERMISSION"))) {
            throw new AccessDeniedException("User is not authorized to view permissions");
        }
    }

    @Override
    public void canManagePermissions() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_PERMISSION"))) {
            throw new AccessDeniedException("User is not authorized to manage permissions");
        }
    }

    @Override
    public void canViewPosts() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_POST"))) {
            throw new AccessDeniedException("User is not authorized to view posts");
        }
    }

    @Override
    public void canManagePosts() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_POST"))) {
            throw new AccessDeniedException("User is not authorized to manage posts");
        }
    }

    @Override
    public void canViewQuestions() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_QUESTION"))) {
            throw new AccessDeniedException("User is not authorized to view questions");
        }
    }

    @Override
    public void canManageQuestions() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_QUESTION"))) {
            throw new AccessDeniedException("User is not authorized to manage questions");
        }
    }

    @Override
    public void canViewQuotas() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_QUOTA"))) {
            throw new AccessDeniedException("User is not authorized to view quotas");
        }
    }

    @Override
    public void canManageQuotas() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_QUOTA"))) {
            throw new AccessDeniedException("User is not authorized to manage quotas");
        }
    }

    @Override
    public void canViewRoles() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_ROLE"))) {
            throw new AccessDeniedException("User is not authorized to view roles");
        }
    }

    @Override
    public void canManageRoles() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_ROLE"))) {
            throw new AccessDeniedException("User is not authorized to manage roles");
        }
    }

    @Override
    public void canViewRooms() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_ROOM"))) {
            throw new AccessDeniedException("User is not authorized to view rooms");
        }
    }

    @Override
    public void canManageRooms() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_ROOM"))) {
            throw new AccessDeniedException("User is not authorized to manage rooms");
        }
    }

    @Override
    public void canViewSchools() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_SCHOOL"))) {
            throw new AccessDeniedException("User is not authorized to view schools");
        }
    }

    @Override
    public void canManageSchools() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_SCHOOL"))) {
            throw new AccessDeniedException("User is not authorized to manage schools");
        }
    }

    @Override
    public void canViewSubmissions() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_SUBMISSION"))) {
            throw new AccessDeniedException("User is not authorized to view submissions");
        }
    }

    @Override
    public void canManageSubmissions() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_SUBMISSION"))) {
            throw new AccessDeniedException("User is not authorized to manage submissions");
        }
    }

    @Override
    public void canViewTasks() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_TASK"))) {
            throw new AccessDeniedException("User is not authorized to view tasks");
        }
    }

    @Override
    public void canManageTasks() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_TASK"))) {
            throw new AccessDeniedException("User is not authorized to manage tasks");
        }
    }

    @Override
    public void canViewTestCases() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_TEST_CASE"))) {
            throw new AccessDeniedException("User is not authorized to view test cases");
        }
    }
    @Override
    public void canManageTestCases() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_TEST_CASE"))) {
            throw new AccessDeniedException("User is not authorized to manage test cases");
        }
    }

    @Override
    public void canViewTestGroups() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_TEST_GROUP"))) {
            throw new AccessDeniedException("User is not authorized to view test groups");
        }
    }

    @Override
    public void canManageTestGroups() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_TEST_GROUP"))) {
            throw new AccessDeniedException("User is not authorized to manage test groups");
        }
    }

    @Override
    public void canViewThreads() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_THREAD"))) {
            throw new AccessDeniedException("User is not authorized to view threads");
        }
    }

    @Override
    public void canManageThreads() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_THREAD"))) {
            throw new AccessDeniedException("User is not authorized to manage threads");
        }
    }

    @Override
    public void canViewTopics() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_TOPIC"))) {
            throw new AccessDeniedException("User is not authorized to view topics");
        }
    }

    @Override
    public void canManageTopics() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("MANAGE_TOPIC"))) {
            throw new AccessDeniedException("User is not authorized to manage topics");
        }
    }
}