package basement.friends.backend.service.implementation;

import basement.friends.backend.exception.RequestNotFoundException;
import basement.friends.backend.model.Request;
import basement.friends.backend.model.User;
import basement.friends.backend.repository.RequestRepository;
import basement.friends.backend.service.definition.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    @Override
    public Set<Request> getAllUserRequests(User user) {
        return requestRepository.getAllByUserId(user.getId());
    }

    @Override
    public Request saveRequest(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public void updateRequest(String id, Request request) {
        Request req = requestRepository.findById(id).orElseThrow(RequestNotFoundException::new);
        req.setStatus(request.getStatus());
        req.setUpdateDate(new Date());
        requestRepository.save(req);

    }

    @Override
    public void deleteRequest(String id) {
        Request req = requestRepository.findById(id).orElseThrow(RequestNotFoundException::new);
        requestRepository.delete(req);
    }
}
