import axios from 'axios';

class ApiService {
    LoadArticles() {
        return axios.get("http://127.0.0.1:8080/api/articles");
    }
    fetchArticleById(id){
        return axios.get(`/api/articles/${id}`)
    }

    deleteArticle(id){
        return axios.delete(`/api/articles/${id}`)
    }

    addArticle(Article){
        return axios.post("/api/articles", Article);
    }

    editArticle(id, Article){
        return axios.put(`/api/articles/${id}`, Article);
    }

    loginpage() {
        return axios.get("/login")
    }

    signuppage(){
        return axios.get("/signup");
    }
    logout() {
        return axios.get(`/logout`)
    }

    userinfo() {
        return axios.get('/currentuser')
    }

    oauthuserinfo(){
        return axios.get('oauthcurrentuser')
    }

    deleteuser() {
        return axios.delete(`/deleteuser`)
    }

    changeNickname(nickname) {
        return axios.post("/nickname", nickname)
    }
}

export default new ApiService();