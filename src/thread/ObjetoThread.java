package src.thread;

public class ObjetoThread {

        private String nome;
        private String email;

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ObjetoThread that)) return false;

            if (!nome.equals(that.nome)) return false;
            return email.equals(that.email);
        }

        @Override
        public int hashCode() {
            int result = nome.hashCode();
            result = 31 * result + email.hashCode();
            return result;
        }
}
