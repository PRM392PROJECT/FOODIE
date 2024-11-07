using Foodie.Service.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Foodie.Service.FileManager
{
    public interface IFileService
    {
        Task<FileUploadViewModel> SaveFile(FileUploadViewModel file);
        Task<bool> DeleteFile(string filePath);
        Task<FileUploadViewModel> UpdateFile(FileUploadViewModel file);
    }
}