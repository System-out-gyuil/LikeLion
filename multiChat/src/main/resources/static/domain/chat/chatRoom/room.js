const roomId = 0;
let lastChatMessageId = 0;
const chatMessagesEl = document.querySelector(".chat__messages");

function submitWriteForm(form) {
  form.writerName.value = form.writerName.value.trim();
  if (form.writerName.value.length == 0) {
    alert("작성자 명을 입력해주세요.");
    form.writerName.focus();
    return;
  }
  form.content.value = form.content.value.trim();
  if (form.content.value.length == 0) {
    alert("내용을 입력해주세요.");
    form.content.focus();
    return;
  }
  const action = `/chat/room/${roomId}/write`;
  fetch(action, {
    method: "POST",
    headers: {
      accept: "application/json",
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      writerName: form.writerName.value,
      content: form.content.value
    })
  }).catch((error) => alert(error));
  form.content.value = "";
  form.content.focus();
}

function loadMoreChatMessages() {
  const action = `/chat/room/${roomId}/messagesAfter/${lastChatMessageId}`;
  fetch(action, {
    method: "GET",
    headers: {
      accept: "application/json"
    }
  })
    .then((response) => response.json())
    .then((json) => drawMoreChatMessages(json.data.messages))
    .then(() => setTimeout(loadMoreChatMessages, 500))
    .catch((error) => alert(error));
}

function drawMoreChatMessages(messages) {
  messages.forEach((message) => {
    drawMoreChatMessage(message);
  });
}

function drawMoreChatMessage(message) {
  lastChatMessageId = message.id;
  chatMessagesEl.insertAdjacentHTML(
    "afterBegin",
    `<li>${message.writerName} : ( ${message.id} ) ${message.content}</li>`
  );
}
// loadMoreChatMessages();
