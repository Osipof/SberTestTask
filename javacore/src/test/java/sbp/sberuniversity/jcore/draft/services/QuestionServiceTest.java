package sbp.sberuniversity.jcore.draft.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sbp.sberuniversity.jcore.draft.models.QuestionFindRequest;
import sbp.sberuniversity.jcore.draft.models.QuestionSaveRequest;
import sbp.sberuniversity.jcore.draft.persistence.models.QuestionFindCriteria;
import sbp.sberuniversity.jcore.draft.persistence.repository.QuestionRepository;
import sbp.sberuniversity.jcore.draft.persistence.repository.dao.DifficultyLevelDAO;
import sbp.sberuniversity.jcore.draft.persistence.repository.dao.QuestionDAO;
import sbp.sberuniversity.jcore.draft.services.mappers.QuestionMapper;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public final class QuestionServiceTest {

    private static final PodamFactory PODAM_FACTORY = new PodamFactoryImpl();

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private QuestionMapper questionMapper;

    @InjectMocks
    private QuestionServiceImpl service;

    @Test
    public void testGetList() {
        List<QuestionDAO> questionsDAO = PODAM_FACTORY.manufacturePojo(ArrayList.class, QuestionDAO.class);
        when(questionRepository.getAll()).thenReturn(questionsDAO);

        service.getList();

        verify(questionRepository).getAll();
        verify(questionMapper).map2QuestionResponse(eq(questionsDAO));
    }

    @Test
    public void testGetListByCriteria() {
        final UUID questionId = UUID.randomUUID();
        final DifficultyLevelDAO difficultyLevel = DifficultyLevelDAO.EASY;
        final Boolean multipleChoice = Boolean.TRUE;

        QuestionFindCriteria criteria = new QuestionFindCriteria(questionId, difficultyLevel, multipleChoice);
        when(questionMapper.map2Criteria(any())).thenReturn(criteria);
        initRepositoryMocks();

        QuestionFindRequest criteriaRequest = PODAM_FACTORY.manufacturePojo(QuestionFindRequest.class);
        service.getListByCriteria(criteriaRequest);

        verify(questionMapper).map2Criteria(eq(criteriaRequest));
        verifyRepositoryFindCalls(questionId, difficultyLevel, multipleChoice);
        verify(questionMapper).map2QuestionResponse(any());
    }

    @Test
    public void testSave() {
        QuestionDAO questionDAO = PODAM_FACTORY.manufacturePojo(QuestionDAO.class);
        questionDAO.setQuestionId(null);
        when(questionMapper.map2QuestionDAO(any())).thenReturn(questionDAO);
        when(questionRepository.save(any())).thenReturn(PODAM_FACTORY.manufacturePojo(QuestionDAO.class));

        QuestionSaveRequest saveRequest = PODAM_FACTORY.manufacturePojo(QuestionSaveRequest.class);
        saveRequest.setId(null);
        service.save(saveRequest);

        verify(questionMapper).map2QuestionDAO(eq(saveRequest));
        ArgumentCaptor<QuestionDAO> questionCaptor = ArgumentCaptor.forClass(QuestionDAO.class);
        verify(questionRepository).save(questionCaptor.capture());
        assertNull(questionCaptor.getValue().getQuestionId());
        verify(questionMapper).map2SaveResponse(any());
    }

    @Test
    public void testUpdate() {
        QuestionDAO questionDAO = PODAM_FACTORY.manufacturePojo(QuestionDAO.class);
        when(questionMapper.map2QuestionDAO(any())).thenReturn(questionDAO);
        when(questionRepository.update(any())).thenReturn(PODAM_FACTORY.manufacturePojo(QuestionDAO.class));

        QuestionSaveRequest updateRequest = PODAM_FACTORY.manufacturePojo(QuestionSaveRequest.class);
        service.save(updateRequest);

        verify(questionMapper).map2QuestionDAO(eq(updateRequest));
        ArgumentCaptor<QuestionDAO> questionCaptor = ArgumentCaptor.forClass(QuestionDAO.class);
        verify(questionRepository).update(questionCaptor.capture());
        assertEquals(questionDAO.getQuestionId(), questionCaptor.getValue().getQuestionId());
        verify(questionMapper).map2SaveResponse(any());
    }

    @Test
    public void testDelete() {
        UUID questionId = UUID.randomUUID();

        service.delete(questionId);

        verify(questionRepository).delete(eq(questionId));
        verifyNoMoreInteractions(questionRepository);
    }

    private void initRepositoryMocks() {
        when(questionRepository.findById(any())).thenReturn(PODAM_FACTORY.manufacturePojo(ArrayList.class, QuestionDAO.class));
        when(questionRepository.findByLevel(any())).thenReturn(PODAM_FACTORY.manufacturePojo(ArrayList.class, QuestionDAO.class));
        when(questionRepository.findByMultipleChoice(any())).thenReturn(PODAM_FACTORY.manufacturePojo(ArrayList.class, QuestionDAO.class));
    }

    private void verifyRepositoryFindCalls(UUID questionId, DifficultyLevelDAO difficultyLevel, Boolean multipleChoice) {
        ArgumentCaptor<UUID> questionIdCaptor = ArgumentCaptor.forClass(UUID.class);
        verify(questionRepository).findById(questionIdCaptor.capture());
        assertEquals(questionId, questionIdCaptor.getValue());

        ArgumentCaptor<DifficultyLevelDAO> difficultyLevelCaptor = ArgumentCaptor.forClass(DifficultyLevelDAO.class);
        verify(questionRepository).findByLevel(difficultyLevelCaptor.capture());
        assertEquals(difficultyLevel, difficultyLevelCaptor.getValue());

        ArgumentCaptor<Boolean> multipleChoiceCaptor = ArgumentCaptor.forClass(Boolean.class);
        verify(questionRepository).findByMultipleChoice(multipleChoiceCaptor.capture());
        assertEquals(multipleChoice, multipleChoiceCaptor.getValue());
    }

}
