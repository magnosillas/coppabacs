"use client"

import Image from "next/image";
import style from "./list.module.scss";

import { useEffect, useState } from "react";
import { useMutation } from "react-query";

import Link from "next/link";
import Header from "../HeaderNavegacao";
import Table from "./Table";

import { Search, SearchUsuarios } from "../searchUsuario";
import { getAllCoppabacs } from "@/api/usuarios/coppabacs/getAllCoppabacs";
import DetalhamentoUsuario from "../DetalhamentoUsuario";

export default function ListFuncionarios({ diretorioAnterior, diretorioAtual, hrefAnterior, table1, table2, table3, table4 }) {

  const [funcionarios, setFuncionarios] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');

  const [selectedFuncionario, setSelectedFuncionario] = useState(null);

  useEffect(() => {
    mutate();
  }, [])

  const { status, mutate } = useMutation(
    async () => {
      return getAllCoppabacs();
    }, {
    onSuccess: (res) => {
      setFuncionarios(res.data);
    },
    onError: (error) => {
      console.error(error);
    }
  }
  );


  const listFuncionarios = funcionarios.filter((funcionarios) =>
    funcionarios.nome.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const handleSelectFuncionario = (funcionario) => {
    setSelectedFuncionario(funcionario)
  }

  const handleBackToList = () => {
    setSelectedFuncionario(null)
  }

  if (selectedFuncionario) {
    return (
      <DetalhamentoUsuario
        usuario={selectedFuncionario}
        backDetalhamento={handleBackToList}
        hrefAnterior="/funcionarios"
      />
    )
  }

  return (
    <div>
      <Header
        diretorioAnterior={diretorioAnterior}
        diretorioAtual={diretorioAtual}
        hrefAnterior={hrefAnterior}
      />
      <div className={style.header}>
        <div className={style.header__container}>

          <button>

            <Link className={style.header__container_link} href="funcionarios/novoFuncionario">
              <h1>
                Adicionar Funcionário(a)
              </h1>
            </Link>

            <Image src="/assets/iconMaisAgricultor.svg" alt="Adicionar Novo Funcionario" width={27} height={24} />
          </button>
          <div className={style.header__container_buttons}>

          </div>

        </div>
      </div>

      <Search searchTerm={searchTerm} setSearchTerm={setSearchTerm} />
      {listFuncionarios && (
        <Table
          table1={table1}
          table2={table2}
          table3={table3}
          table4={table4}
          listFuncionarios={listFuncionarios}
          setFuncionarios={setFuncionarios}
          onSelectFuncionario={handleSelectFuncionario}
        />
      )}
    </div>
  )
}
