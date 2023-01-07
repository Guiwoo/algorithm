package model

import "time"

type memoryHanlder struct {
	todoMap map[int]*Todo
}

func (m *memoryHanlder) GetTodos(sessionId string) []*Todo {
	list := []*Todo{}
	for _, v := range m.todoMap {
		list = append(list, v)
	}
	return list
}
func (m *memoryHanlder) AddTodo(name, sessionId string) *Todo {
	id := len(m.todoMap)
	todo := &Todo{id, name, false, time.Now()}
	m.todoMap[id] = todo
	return todo
}
func (m *memoryHanlder) RemoveTodo(id int) bool {
	if _, ok := m.todoMap[id]; ok {
		delete(m.todoMap, id)
		return true
	}
	return false
}
func (m *memoryHanlder) CompleteTodo(id int, complete bool) bool {
	if todo, ok := m.todoMap[id]; ok {
		todo.Completed = complete
		return true
	}
	return false
}
func (m *memoryHanlder) Close() {}

func newMemoryHandler() DBHandler {
	m := &memoryHanlder{}
	m.todoMap = make(map[int]*Todo)
	return m
}
