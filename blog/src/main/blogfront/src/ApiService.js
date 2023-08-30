import axios from 'axios';

const BLOG_API_URL = "http://localhost:8080/api/articles"

class ApiService {
    LoadArticles() {
        return axios.get(BLOG_API_URL);
    }
    fetchArticleById(id){
        return axios.get(`http://localhost:8080/api/articles/${id}`)
    }

    deleteArticle(id){
        return axios.delete(`http://localhost:8080/api/articles/${id}`)
    }

    addArticle(Article){
        return axios.post(BLOG_API_URL, Article);
    }

    editArticle(id, Article){
        return axios.put(`http://localhost:8080/api/articles/${id}`, Article);
    }

    loginpage(){
        return axios.get("http://localhost:8080/login")
    }

    signuppage(){
        return axios.get("http://localhost:8080/signup")
    }


}

export default new ApiService();