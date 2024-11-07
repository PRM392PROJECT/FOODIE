using Microsoft.AspNetCore.Http;
using System.ComponentModel.DataAnnotations;
using System.Diagnostics.CodeAnalysis;

namespace Foodie.Service.Models
{
    public class FileUploadViewModel
    {
        [Required(ErrorMessage = "Please select a file.")]
        [DataType(DataType.Upload)]
        public IFormFile File { get; set; }

        [Required(ErrorMessage = "Please input file name.")]
        public string FileName { get; set; }

        public string? FilePath { get; set; }
    }
}