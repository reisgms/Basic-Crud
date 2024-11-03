let tasks = [];
    const tasksPerPage = 5;
    let currentPage = 1;

    function addTask() {
      const taskName = document.getElementById("taskName").value.trim();
      const taskCost = parseFloat(document.getElementById("taskCost").value);
      const taskDeadline = document.getElementById("taskDeadline").value;

      if (!taskName || isNaN(taskCost) || !taskDeadline) {
        alert("Por favor, preencha todos os campos da tarefa.");
        return;
      }

      const task = {
        id: tasks.length + 1,
        name: taskName,
        cost: taskCost,
        deadline: taskDeadline,
      };

      tasks.push(task);
      clearInputs();
      renderTasks();
      renderPagination();
    }

    function clearInputs() {
      document.getElementById("taskName").value = "";
      document.getElementById("taskCost").value = "";
      document.getElementById("taskDeadline").value = "";
    }

    function renderTasks() {
      const taskList = document.getElementById("taskList");
      taskList.innerHTML = "";

      const startIndex = (currentPage - 1) * tasksPerPage;
      const endIndex = startIndex + tasksPerPage;
      const tasksToDisplay = tasks.slice(startIndex, endIndex);

      tasksToDisplay.forEach((task, index) => {
        const taskRow = document.createElement("tr");
        taskRow.draggable = true;
        taskRow.dataset.index = startIndex + index;
        taskRow.className = task.cost >= 1000 ? "highlight" : "";

        taskRow.addEventListener("dragstart", handleDragStart);
        taskRow.addEventListener("dragover", handleDragOver);
        taskRow.addEventListener("drop", handleDrop);

        taskRow.innerHTML = `
          <td>${task.id}</td>
          <td>${task.name}</td>
          <td>R$ ${task.cost.toFixed(2)}</td>
          <td>${task.deadline}</td>
          <td>
            <button class="edit-btn" onclick="editTask(${task.id})">Modificar</button>
            <button class="delete-btn" onclick="confirmDelete(${task.id})">Excluir</button>
          </td>
        `;

        taskList.appendChild(taskRow);
      });
    }

    function renderPagination() {
      const pagination = document.getElementById("pagination");
      pagination.innerHTML = "";

      const totalPages = Math.ceil(tasks.length / tasksPerPage);
      for (let i = 1; i <= totalPages; i++) {
        const pageButton = document.createElement("button");
        pageButton.innerText = i;
        pageButton.className = i === currentPage ? "active" : "";
        pageButton.onclick = () => {
          currentPage = i;
          renderTasks();
          renderPagination();
        };
        pagination.appendChild(pageButton);
      }
    }

    function editTask(id) {
      const task = tasks.find(t => t.id === id);
      if (task) {
        const newName = prompt("Modificar nome da tarefa:", task.name);
        const newCost = prompt("Modificar custo da tarefa:", task.cost);
        const newDeadline = prompt("Modificar data limite:", task.deadline);

        if (newName && newCost && newDeadline) {
          task.name = newName;
          task.cost = parseFloat(newCost);
          task.deadline = newDeadline;
          renderTasks();
        }
      }
    }

    function confirmDelete(id) {
      if (confirm("Tem certeza que deseja excluir esta tarefa?")) {
        tasks = tasks.filter(t => t.id !== id);
        renderTasks();
        renderPagination();
      }
    }

    let draggedIndex;

    function handleDragStart(event) {
      draggedIndex = event.target.dataset.index;
      event.target.classList.add("draggable");
    }

    function handleDragOver(event) {
      event.preventDefault();
    }

    function handleDrop(event) {
      const droppedIndex = event.target.closest("tr").dataset.index;
      [tasks[draggedIndex], tasks[droppedIndex]] = [tasks[droppedIndex], tasks[draggedIndex]];
      renderTasks();
    }

    renderTasks();