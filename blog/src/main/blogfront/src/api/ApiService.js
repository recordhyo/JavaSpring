import axios from 'axios';

class ApiService {
    LoadArticles() {
        return axios.get("/api/articles");
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

    loginpage(){
        return axios.get("/login")
    }

    signuppage(){
        return axios.get("/signup")
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

}

export default new ApiService();