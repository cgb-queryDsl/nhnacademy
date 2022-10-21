기능 1. 모든 사용자는 게시글(article) 내용의 목록을 볼 수 있습니다.

```HTTP
GET /articles/list/all/1
```

 ```json
{
    "lists": 10,
    "listSize": 20,
    "list": [
        {"articleId": "1987r9836",
        "articleTitle": "아이패드 꿀팁",
        "writer": "아이패드 오너1",
        "modifiers": [ 
            {"modifierName": "아이패드 오너2"},
            {"modifierName": "아이패드 오너3"} ],
        "writeTime":"2022-10-21:09:40:39",
        "comments":7}

        {
        "articleId": "aa1381",
        "articleTitle": "아이폰 구매 팁",
        "writer": "광주 무등산러버",
        "modifiers": [ 
            {"modifierName": "애플러버1"},
            {"modifierName": "삼성러버1"} ],
        "writeTime": "2022-08-02:23:40:39",
        "comments":2}
        ...
    ],
}
 ```

 ---

기능 2. 모든 사용자는 게시글의 내용을 볼 수 있습니다.

```HTTP
GET /articles/article/{articleId}
```

```json
{
    "articleNum": 1803,
    "articleTitle": "아메리카노의 유래",
    "contents": "아메리카노는 미국에서 시작되어 .....",
    "writer": "커피를 사랑하는 웰시코기 견주",
    "modifiers": [ 
            {"modifierName": "아이패드 오너2"},
            {"modifierName": "아이패드 오너3"} ],
    "writeTime": "2018-08-18:08:08:19",
    "modifyTimes": [
        {"modifierTime": "2019-12-25:10:14:23"},
        {"modifierTime": "2020-01-25:10:14:23"} ],
    "commentsList": [
        {"comment": "재밌어요"},
        {"comment": "이 부분에 오타 있어요~"}]
}
```

---

기능 3. 로그인 한 사용자는 게시판에 게시글을 등록할 수 있습니다.
```
POST /articles/write?login=ok
```
---

기능 4. 게시글 내용을 작성한 사람은 내용을 수정하거나 삭제할 수 있습니다.
수정 
```
PATCH /articles/{articleId}?login=ok
```

삭제 
```
DELETE /articles/{articleId}?login=ok
```

---

기능 5. 관리자는 모든 게시글 내용을 수정 할 수 있습니다.
```
PATCH /admin/articles/{articleId}
```

---

기능 6. 관리자는 모든 게시글 내용을 삭제 할 수 있습니다.
```
DELETE /admin/articles/{articleId}
```

---

기능 7. 관리자는 삭제한 게시글 내용을 복구 할 수 있습니다.
```
POST /admin/garbages/{articleId}
```

---

기능 8. 로그인 한 사용자는 게시글의 내용을 보고 댓글(comment)을 등록할 수 있습니다.

```
POST /articles/article/{articleId}/comment?login=ok
```

```json
{
    "errorMsg": "댓글이 없습니다."
}
```
```json
{
    "errorMsg": "없는 게시글 입니다."
}
```
```json
{
    "errorMsg": "권한이 없습니다."
}
```

----

추가기능 1.1 로그인한 사용자는 게시글 내용에 대해 좋아요를 등록할 수 있습니다.
```
POST /articles/article/{articleId}/like?login=ok
```

추가기능 1.2 좋아요를 등록한 사용자는 좋아요 취소를 할 수 있습니다.
```
PATCH /articles/article/{articleId}/like?login=ok
```

---

추가기능 2.1 로그인한 사용자는 게시글 내용에 대해 답글(reply)을 쓸 수 있습니다.
```
POST /articles/{articleId}/reply?login=ok
```

추가기능 2.2 로그인한 사용자는 게시글 내용에 대해 답글에 대한 답글을 등록할 수 있습니다.
```
POST /articles/{articleId}/reply/{replyId}?login=ok
```
추가기능 2.3 게시글 목록에서 답글은 최대 5 단계까지 깊이를 제공합니다.
```
GET /articles/{articleId}/list/all/1
```
---

추가기능 3.1 좋아요를 등록 사용자는 좋아요한 게시글 내용 목록을 조회할 수 있습니다.
```
GET /articles/list/likes/all/1
```

```json
{
    "likeSize" : 5,
    "articleList": [
        {"articleTitle": "게시글 1"},
        {"articleTitle": "게시글 2"},
        {"articleTitle": "게시글 3"},
        {"articleTitle": "게시글 4"},
        {"articleTitle": "게시글 5"} ]
}
```

추가기능 3.2 모든 사용자는 제목에 대한 게시글 목록을 검색할 수 있습니다.
```
GET /search?value={articleTitle}
```

```json
{
    "resultSize": 5,
    "articleList": [
        {"articleTitle": "게시글 1"},
        {"articleTitle": "게시글 2"},
        {"articleTitle": "게시글 3"},
        {"articleTitle": "게시글 4"},
        {"articleTitle": "게시글 5"} ]
}
```

---
**더 생각해보면**
- 게시글에 목록에 정렬 기능을 제공한다면, 어떻게 제공할 수 있을까요?
    > 주어진 리스트 데이터를 가지고 정렬을 로직을 추가하면 될 거 같습니다.
- 게시판이 여러개가 있다면 어떤 URL 구조여야 할까요?
    > /baseball/articles  
    > /soccer/articles  
    > 처음에 종류별로 나눠주면 될거 같습니다.
- 임시보관함이 있고 이어 쓸 수 있으려면 어떻게 해야할까요? 
    > 임시보관함 데이터를 GET으로 얻고 PATCH로 수정된 데이터를 보내면 될 것 같습니다.
- 임시보관된 게시글은 최종적으로 다른 게시판에 등록할 수 있다면 어떻게 해야할까요?
    > POST 메서드를 통해 다른 게시판 경로에 맞게 보내주면 괜찮을 거 같습니다.
- 게시글이 여러 게시판에서 볼 수 있게 정의가 되면 URL 경로가 어떻게 되어야 할까요?
    > ..?
- 특정 게시판의 글을 다른 게시판으로 옮기려면 어떻게 해야할까요?
    > /{다른게시판} 쪽으로 POST 메시지로 옮기면 될 것 같습니다.
- 파일 업로드는 어떤 경로로 만드는 것이 좋을까요?
    > 게시글 업로드 방식과 비슷할 것 같습니다.
- API 버전을 표시해야 한다면 어떻게 하는 것이 좋을까요? 
    > v1  
    > v1.1  
    > v2  
    > ...
- 에러는 어떻게 표현하는 것이 좋을까요?
    > 40x, 50x 에러코드를 표현하는게 좋을거 같습니다.
- 게시물의 조회수가 올라가야 한다면, 어떻게 설계하면 좋을까요?
    > json 데이터에 조회수도 추가하고 GET 메서드도 추가하면 될 거 같습니다.