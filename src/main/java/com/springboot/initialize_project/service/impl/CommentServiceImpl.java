package com.springboot.initialize_project.service.impl;

/*@Service
public class CommentServiceImpl implements CommentService {

    public CommentRepository commentRepository;
    public JwtTokenProvider jwtTokenProvider;
    public UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,
                              JwtTokenProvider jwtTokenProvider,
                              UserRepository userRepository){
        this.commentRepository = commentRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @Override
    public void createComment(String content, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        String token = jwtTokenProvider.resolveToken(servletRequest);
        String account = jwtTokenProvider.getAccount(token);

        Comment newComment = new Comment();
        newComment.setUser(userRepository.getByAccount(account));
        newComment.setContent(content);
        commentRepository.save(newComment);
    }
}*/
